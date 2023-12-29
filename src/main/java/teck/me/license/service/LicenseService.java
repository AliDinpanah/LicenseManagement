package teck.me.license.service;

import teck.me.license.model.dto.LicenseDto;

import java.util.List;

public interface LicenseService {

    List<LicenseDto> getAllLicenses(int page,int number) throws IllegalAccessException;

    LicenseDto getLicenseById(String uuid) throws IllegalAccessException;

    LicenseDto saveLicense(LicenseDto licenseDto) throws IllegalAccessException;

    LicenseDto updateLicense(String uuid,LicenseDto updatedLicenseDto) throws IllegalAccessException;

    void deleteLicense(String uuid);

    LicenseDto createLicense(int validityDuration, String cryptoKeyId, Long projectId, Long customerId) throws IllegalAccessException;

    LicenseDto parameterLimit(String uuid,String projectParameter,String limitation) throws IllegalAccessException;
}
