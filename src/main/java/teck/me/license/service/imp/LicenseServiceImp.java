package teck.me.license.service.imp;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import teck.me.license.exception.NotFoundException;
import teck.me.license.model.CryptoKey;
import teck.me.license.model.Customer;
import teck.me.license.model.License;
import teck.me.license.model.Project;
import teck.me.license.model.dto.*;
import teck.me.license.repository.LicenseRepository;
import teck.me.license.service.LicenseService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class LicenseServiceImp implements LicenseService {

    private final LicenseRepository licenseRepository;

    private final ProjectServiceImp projectServiceImp;

    private final CustomerServiceImp customerServiceImp;

    private final CryptoKeyServiceImp cryptoKeyServiceImp;

    public LicenseServiceImp(LicenseRepository licenseRepository, ProjectServiceImp projectServiceImp, CustomerServiceImp customerServiceImp,@Lazy CryptoKeyServiceImp cryptoKeyServiceImp) {
        this.licenseRepository = licenseRepository;
        this.projectServiceImp = projectServiceImp;
        this.customerServiceImp = customerServiceImp;
        this.cryptoKeyServiceImp = cryptoKeyServiceImp;
    }

    public List<LicenseDto> getAllLicenses(int page, int number) {
        Pageable pageable = PageRequest.of(page, number);
        Page<License> licensePage = licenseRepository.findAll(pageable);
        List<LicenseDto> licenseDtos = new ArrayList<>();

        for (License license : licensePage.getContent()) {
            licenseDtos.add(new LicenseDto(license.getValidityDuration(), license.getTakeEffectTime(),
                    license.getCryptoKey().getUuid(), license.getProject().getName(), license.getCustomer().getName(),
                    license.getParameters(), license.getDescription()));
        }

        return licenseDtos;
    }


    public LicenseDto getLicenseById(String uuid) {
        if (licenseRepository.findByUuid(uuid).isPresent()) {
            License license = licenseRepository.findByUuid(uuid).get();
            return new LicenseDto(license.getValidityDuration(), license.getTakeEffectTime(),
                    license.getCryptoKey().getUuid(), license.getProject().getName(), license.getCustomer().getName(),
                    license.getParameters(), license.getDescription());
        }
        throw new NotFoundException("No License with this id");
    }

    public CreateLicenseDto updateLicense(String uuid, CreateLicenseDto updatedLicenseDto) {

        if (licenseRepository.findByUuid(uuid).isPresent()) {
            License license = licenseRepository.findByUuid(uuid).get();

            license.setParameters(updatedLicenseDto.getParameters());
            license.setProject(projectServiceImp.getProject(updatedLicenseDto.getProjectName()));
            license.setCustomer(customerServiceImp.getCustomer(updatedLicenseDto.getCustomerName()));
            license.setCryptoKey(cryptoKeyServiceImp.getCryptoKey(updatedLicenseDto.getCryptoKeyUuid()));
            license.setTakeEffectTime(updatedLicenseDto.getTakeEffectTime());
            license.setTakeEffectTime(updatedLicenseDto.getTakeEffectTime());
            licenseRepository.save(license);

            return updatedLicenseDto;
        }
        throw new NotFoundException("No License with this id");
    }

    @Transactional
    public void deleteLicense(String uuid) {
        licenseRepository.deleteByUuid(uuid);
    }


    public CreateLicenseDto createLicense(CreateLicenseDto licenseDto, String cryptoKeyId, String projectName, String customerName) {
        Project project = projectServiceImp.getProject(projectName);
        CryptoKey cryptoKey;

        Optional<CryptoKey> optionalCryptoKey = project.getCryptoKeys().stream()
                .filter(key -> key.getUuid().equals(cryptoKeyId))
                .findFirst();

        if (optionalCryptoKey.isPresent()) {
            cryptoKey = optionalCryptoKey.get();
        } else {
            //default crypto key
            cryptoKey = new CryptoKey();
            cryptoKey.setProject(project);
            cryptoKey.setDescription("description");
            cryptoKey.setUuid("112233");
            cryptoKeyServiceImp.saveCryptoKey(cryptoKey);
        }

        Customer customer = customerServiceImp.getCustomer(customerName);
        License license = new License();


        license.setValidityDuration(licenseDto.getValidityDuration());
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
        license.setDescription(licenseDto.getDescription());

        while (true) {
            license.setUuid(UUID.randomUUID().toString());
            if (!licenseRepository.existsByUuid(license.getUuid())) {
                System.out.println("License: " + license.getUuid());
                break;
            }
        }
        licenseRepository.save(license);
        return new CreateLicenseDto(license.getValidityDuration(), license.getTakeEffectTime(),
                license.getCryptoKey().getUuid(), license.getProject().getName(), license.getCustomer().getName(),
                new HashMap<>(), license.getDescription());
    }

    public LicenseDto parameterLimit(String uuid, String projectParameter, String limitation) {
        if (licenseRepository.findByUuid(uuid).isPresent()) {
            License license = licenseRepository.findByUuid(uuid).get();
            Project project = license.getProject();


            for (String str : project.getParameters()) {
                if (str.equals(projectParameter)) {

                    license.getParameters().put(str, limitation);
                    licenseRepository.save(license);

                    return new LicenseDto(license.getValidityDuration(), license.getTakeEffectTime(),
                            license.getCryptoKey().getUuid(), license.getProject().getName(), license.getCustomer().getName(),
                            license.getParameters(), license.getDescription());
                }
            }
            throw new NotFoundException("Project doesn't have this parameter");
        }
        throw new NotFoundException("No License with this id");
    }

    //use in other services
    public License getLicense(String uuid) {
        if (licenseRepository.findByUuid(uuid).isPresent()) {
            return licenseRepository.findByUuid(uuid).get();
        }
        throw new NotFoundException("No License with this id");
    }

}
