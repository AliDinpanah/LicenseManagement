package teck.me.license.service;

import teck.me.license.model.License;
import teck.me.license.model.dto.CreateLicenseDto;
import teck.me.license.model.dto.LicenseDto;

import java.util.List;

public interface LicenseService {

    List<LicenseDto> getAllLicenses(int page, int number);

    LicenseDto getLicenseById(String uuid);

    CreateLicenseDto updateLicense(String uuid, CreateLicenseDto updatedLicenseDto);

    void deleteLicense(String uuid);

    CreateLicenseDto createLicense(CreateLicenseDto licenseDto, String cryptoKeyId, String projectName, String customerName);

    LicenseDto parameterLimit(String uuid, String projectParameter, String limitation);

    License getLicense(String uuid);
}
