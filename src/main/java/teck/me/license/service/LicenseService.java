package teck.me.license.service;

import teck.me.license.model.dto.LicenseDto;

import java.util.List;

public interface LicenseService {

    List<LicenseDto> getAllLicenses(int page,int number);

    LicenseDto getLicenseById(String uuid);

    LicenseDto saveLicense(LicenseDto licenseDto);

    LicenseDto updateLicense(String uuid,LicenseDto updatedLicenseDto);

    void deleteLicense(String uuid);

    LicenseDto createLicense(int validityDuration, String cryptoKeyId, Long projectId, Long customerId);

    LicenseDto parameterLimit(String uuid,String projectParameter,String limitation);
}
