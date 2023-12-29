package teck.me.license.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import teck.me.license.exception.NotFoundException;
import teck.me.license.model.CryptoKey;
import teck.me.license.model.Customer;
import teck.me.license.model.License;
import teck.me.license.model.Project;
import teck.me.license.model.dto.CryptoKeyDto;
import teck.me.license.model.dto.CustomerDto;
import teck.me.license.model.dto.LicenseDto;
import teck.me.license.model.dto.ProjectDto;
import teck.me.license.repository.LicenseRepository;
import teck.me.license.service.LicenseService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import teck.me.license.utill.Converter;

import java.util.*;

@Service
public class LicenseServiceImp implements LicenseService {
    @Autowired
    private LicenseRepository licenseRepository;
    @Autowired
    private ProjectServiceImp projectServiceImp;
    @Autowired
    private CustomerServiceImp customerServiceImp;

    private Converter<Project, ProjectDto> convertProjectToDto;
    private Converter<ProjectDto, Project> convertDtoToProject;
    private Converter<CryptoKey, CryptoKeyDto> convertCryptoKeyToDto;
    private Converter<CryptoKeyDto, CryptoKey> convertDtoToCryptoKey;
    private Converter<Customer, CustomerDto> convertCustomerToDto;
    private Converter<CustomerDto, Customer> convertDtoToCustomer;

    public List<LicenseDto> getAllLicenses(int page, int number) throws IllegalAccessException {
        Pageable pageable = PageRequest.of(page, number);
        Page<License> licensePage = licenseRepository.findAll(pageable);
        List<LicenseDto> licenseDtos = new ArrayList<>();

        for (License license : licensePage.getContent()) {
            licenseDtos.add(new LicenseDto(license.getValidityDuration(), license.getTakeEffectTime(),
                    convertCryptoKeyToDto.convert(license.getCryptoKey(), new CryptoKeyDto()),
                    convertProjectToDto.convert(license.getProject(), new ProjectDto()),
                    convertCustomerToDto.convert(license.getCustomer(), new CustomerDto()),
                    license.getParameters(), license.getDescription()));
        }

        return licenseDtos;
    }


    public LicenseDto getLicenseById(String uuid) throws IllegalAccessException {
        if (licenseRepository.existsByUuid(uuid)) {
            License license = licenseRepository.findByUuid(uuid).get();
            return new LicenseDto(license.getValidityDuration(), license.getTakeEffectTime(),
                    convertCryptoKeyToDto.convert(license.getCryptoKey(), new CryptoKeyDto()),
                    convertProjectToDto.convert(license.getProject(), new ProjectDto()),
                    convertCustomerToDto.convert(license.getCustomer(), new CustomerDto()),
                    license.getParameters(), license.getDescription());
        }
        throw new NotFoundException("No License with this id");
    }

    public LicenseDto saveLicense(LicenseDto licenseDto) throws IllegalAccessException {
        License license = new License();
        license.setParameters(licenseDto.getParameters());
        license.setProject(convertDtoToProject.convert(licenseDto.getProject(),new Project()));
        license.setCustomer(convertDtoToCustomer.convert(licenseDto.getCustomer(),new Customer()));
        license.setCryptoKey(convertDtoToCryptoKey.convert(licenseDto.getCryptoKey(),new CryptoKey()));
        license.setTakeEffectTime(licenseDto.getTakeEffectTime());
        license.setTakeEffectTime(licenseDto.getTakeEffectTime());
        licenseRepository.save(license);
        return licenseDto;
    }

    public LicenseDto updateLicense(String uuid, LicenseDto updatedLicenseDto) throws IllegalAccessException {

        if (licenseRepository.existsByUuid(uuid)) {
            License license = licenseRepository.findByUuid(uuid).get();

            license.setParameters(updatedLicenseDto.getParameters());
            license.setProject(convertDtoToProject.convert(updatedLicenseDto.getProject(),new Project()));
            license.setCustomer(convertDtoToCustomer.convert(updatedLicenseDto.getCustomer(),new Customer()));
            license.setCryptoKey(convertDtoToCryptoKey.convert(updatedLicenseDto.getCryptoKey(),new CryptoKey()));
            license.setTakeEffectTime(updatedLicenseDto.getTakeEffectTime());
            license.setTakeEffectTime(updatedLicenseDto.getTakeEffectTime());
            licenseRepository.save(license);

            return updatedLicenseDto;
        }
        throw new NotFoundException("No License with this id");
    }

    public void deleteLicense(String uuid) {
        licenseRepository.deleteByUuid(uuid);
    }

    public LicenseDto createLicense(int validityDuration, String cryptoKeyId, Long projectId, Long customerId) throws IllegalAccessException {
        Project project = projectServiceImp.getProject(projectId);
        CryptoKey cryptoKey = projectServiceImp.findKey(cryptoKeyId);
        Customer customer = customerServiceImp.getCustomer(customerId);
        License license = new License();


        license.setValidityDuration(validityDuration);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 3);
        calendar.set(Calendar.MINUTE, 30);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        license.setTakeEffectTime(calendar.getTime().getTime() / 1000);


        license.setCryptoKey(cryptoKey);
        license.setCustomer(customer);
        license.setProject(project);

        while (true) {
            license.setUuid(UUID.randomUUID().toString());
            if (!licenseRepository.existsByUuid(license.getUuid())) {
                break;
            }
        }
        licenseRepository.save(license);

        return new LicenseDto(license.getValidityDuration(), license.getTakeEffectTime(),
                convertCryptoKeyToDto.convert(license.getCryptoKey(), new CryptoKeyDto()),
                convertProjectToDto.convert(license.getProject(), new ProjectDto()),
                convertCustomerToDto.convert(license.getCustomer(), new CustomerDto()),
                license.getParameters(), license.getDescription());
    }

    public LicenseDto parameterLimit(String uuid, String projectParameter, String limitation) throws IllegalAccessException {
        if (licenseRepository.existsByUuid(uuid)) {
            License license = licenseRepository.findByUuid(uuid).get();
            Project project = license.getProject();


            for (String str : project.getParameters()) {
                if (str.equals(projectParameter)) {

                    license.getParameters().put(str, limitation);
                    licenseRepository.save(license);

                    return new LicenseDto(license.getValidityDuration(), license.getTakeEffectTime(),
                            convertCryptoKeyToDto.convert(license.getCryptoKey(), new CryptoKeyDto()),
                            convertProjectToDto.convert(license.getProject(), new ProjectDto()),
                            convertCustomerToDto.convert(license.getCustomer(), new CustomerDto()),
                            license.getParameters(), license.getDescription());
                }
            }
            throw new NotFoundException("Project doesn't have this parameter");
        }
        throw new NotFoundException("No License with this id");
    }

}
