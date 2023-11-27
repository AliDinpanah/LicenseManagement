package teck.me.license.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teck.me.license.model.CryptoKey;
import teck.me.license.model.Customer;
import teck.me.license.model.License;
import teck.me.license.model.Project;
import teck.me.license.model.dto.LicenseDto;
import teck.me.license.repository.LicenseRepository;
import teck.me.license.service.LicenseService;

import java.util.*;

@Service
public class LicenseServiceImp implements LicenseService {
    @Autowired
    private LicenseRepository licenseRepository;
    @Autowired
    private ProjectServiceImp projectServiceImp;
    @Autowired
    private CustomerServiceImp customerServiceImp;

    public List<LicenseDto> getAllLicenses(){
        List<License> licenses = licenseRepository.findAll();
        List<LicenseDto> licenseDtos = new ArrayList<>();
        for (License license:licenses){
            licenseDtos.add(new LicenseDto(license.getValidityDuration(), license.getTakeEffectTime(),
                    license.getCryptoKey(), license.getProject(), license.getCustomer()));
        }
        return licenseDtos;
    }

    public LicenseDto getLicenseById(String uuid) {
        License license = licenseRepository.findByUuid(uuid).get();
        return new LicenseDto(license.getValidityDuration(), license.getTakeEffectTime(),
                license.getCryptoKey(), license.getProject(), license.getCustomer());
    }

    public LicenseDto saveLicense(LicenseDto licenseDto){
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

    public LicenseDto updateLicense(String uuid,LicenseDto updatedLicenseDto){
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
        licenseRepository.save(license);

        return new LicenseDto(validityDuration, calendar.getTime(), cryptoKey, project, customer);
    }

    public LicenseDto parameterLimit(Long id, String projectParameter, String limitation) {
        License license = licenseRepository.findById(id).get();
        Project project = license.getProject();
        Map<String, String> licenseParameter = license.getParameters();


        for (String str : project.getParameters()) {
            if (str.equals(projectParameter)) {
                licenseParameter.put(str, limitation);

                license.setParameters(licenseParameter);
                licenseRepository.save(license);

                return new LicenseDto(licenseParameter);
            }
        }

        throw new NullPointerException();
    }

}