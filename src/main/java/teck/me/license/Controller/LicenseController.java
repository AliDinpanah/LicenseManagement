package teck.me.license.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teck.me.license.model.dto.LicenseDto;
import teck.me.license.service.imp.LicenseServiceImp;

import java.util.List;

@RestController
@RequestMapping("/api/licenses")
public class LicenseController {
    private final LicenseServiceImp licenseService;

    @Autowired
    public LicenseController(LicenseServiceImp licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping
    public ResponseEntity<List<LicenseDto>> getAllLicenses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int number) {
        List<LicenseDto> licenses = licenseService.getAllLicenses(page, number);
        return new ResponseEntity<>(licenses, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<LicenseDto> getLicenseById(@PathVariable String uuid) {
        LicenseDto license = licenseService.getLicenseById(uuid);
        return new ResponseEntity<>(license, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<LicenseDto> saveLicense(@RequestBody LicenseDto licenseDto) {
        LicenseDto savedLicense = licenseService.saveLicense(licenseDto);
        return new ResponseEntity<>(savedLicense, HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<LicenseDto> updateLicense(@PathVariable String uuid, @RequestBody LicenseDto updatedLicense) {
        LicenseDto license = licenseService.updateLicense(uuid, updatedLicense);
        return new ResponseEntity<>(license, HttpStatus.OK);

    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteLicense(@PathVariable String uuid) {
        licenseService.deleteLicense(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create")
    public ResponseEntity<LicenseDto> createLicense(@RequestParam int validityDuration,
                                                    @RequestParam String cryptoKeyId,
                                                    @RequestParam Long projectId,
                                                    @RequestParam Long customerId) {
        LicenseDto createdLicense = licenseService.createLicense(validityDuration, cryptoKeyId, projectId, customerId);
        return new ResponseEntity<>(createdLicense, HttpStatus.CREATED);
    }

    @PostMapping("/parameter-limit/{id}")
    public ResponseEntity<LicenseDto> parameterLimit(@PathVariable String uuid,
                                                     @RequestParam String projectParameter,
                                                     @RequestParam String limitation) {

        LicenseDto updatedLicense = licenseService.parameterLimit(uuid, projectParameter, limitation);
        return new ResponseEntity<>(updatedLicense, HttpStatus.OK);

    }
}
