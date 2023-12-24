package teck.me.license.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import teck.me.license.exception.NotFoundException;
import teck.me.license.model.CryptoKey;
import teck.me.license.model.Customer;
import teck.me.license.model.License;
import teck.me.license.model.Project;
import teck.me.license.model.dto.LicenseDto;
import teck.me.license.repository.LicenseRepository;
import teck.me.license.service.LicenseService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

@Service
public class LicenseServiceImp implements LicenseService {
    @Autowired
    private LicenseRepository licenseRepository;
    @Autowired
    private ProjectServiceImp projectServiceImp;
    @Autowired
    private CustomerServiceImp customerServiceImp;

    public List<LicenseDto> getAllLicenses(int page, int number) {
        Pageable pageable = PageRequest.of(page, number);
        Page<License> licensePage = licenseRepository.findAll(pageable);
        List<LicenseDto> licenseDtos = new ArrayList<>();

        for (License license : licensePage.getContent()) {
            licenseDtos.add(new LicenseDto(license));
        }

        return licenseDtos;
    }


    public LicenseDto getLicenseById(String uuid) {
        if (licenseRepository.existsByUuid(uuid)) {
            License license = licenseRepository.findByUuid(uuid).get();
            return new LicenseDto(license.getValidityDuration(), license.getTakeEffectTime(),
                    license.getCryptoKey(), license.getProject(), license.getCustomer());
        }
        throw new NotFoundException("No License with this id");
    }

    public LicenseDto saveLicense(LicenseDto licenseDto) {
        License license = new License();
        license.setParameters(licenseDto.getParameters());
        license.setProject(licenseDto.getProject());
        license.setCustomer(licenseDto.getCustomer());
        license.setCryptoKey(licenseDto.getCryptoKey());
        license.setTakeEffectTime(licenseDto.getTakeEffectTime());
        license.setTakeEffectTime(licenseDto.getTakeEffectTime());
        licenseRepository.save(license);
        return licenseDto;
    }

    public LicenseDto updateLicense(String uuid, LicenseDto updatedLicenseDto) {

        if (licenseRepository.existsByUuid(uuid)) {
            License license = licenseRepository.findByUuid(uuid).get();

            license.setParameters(updatedLicenseDto.getParameters());
            license.setProject(updatedLicenseDto.getProject());
            license.setCustomer(updatedLicenseDto.getCustomer());
            license.setCryptoKey(updatedLicenseDto.getCryptoKey());
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

    public LicenseDto createLicense(int validityDuration, String cryptoKeyId, Long projectId, Long customerId) {
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
        license.setTakeEffectTime(calendar.getTime());


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

        return new LicenseDto(validityDuration, calendar.getTime(), cryptoKey, project, customer);
    }

    public LicenseDto parameterLimit(String uuid, String projectParameter, String limitation) {
        if (licenseRepository.existsByUuid(uuid)) {
            License license = licenseRepository.findByUuid(uuid).get();
            Project project = license.getProject();
            Map<String, String> licenseParameter = license.getParameters();


            for (String str : project.getParameters()) {
                if (str.equals(projectParameter)) {

                    license.getParameters().put(str, limitation);
                    licenseRepository.save(license);

                    return new LicenseDto(licenseParameter);
                }
            }
            throw new NotFoundException("Project doesn't have this parameter");
        }
        throw new NotFoundException("No License with this id");
    }

}
