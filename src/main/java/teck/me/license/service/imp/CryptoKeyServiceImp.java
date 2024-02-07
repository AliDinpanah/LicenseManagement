package teck.me.license.service.imp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import teck.me.license.exception.NotFoundException;
import teck.me.license.model.CryptoKey;
import teck.me.license.model.License;
import teck.me.license.model.dto.*;
import teck.me.license.repository.CryptoKeyRepository;
import teck.me.license.service.CryptoKeyService;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CryptoKeyServiceImp implements CryptoKeyService {

    private final CryptoKeyRepository cryptoKeyRepository;
    private final ProjectServiceImp projectServiceImp;
    private final LicenseServiceImp licenseServiceImp;


    public CryptoKeyServiceImp(CryptoKeyRepository cryptoKeyRepository, ProjectServiceImp projectServiceImp, LicenseServiceImp licenseServiceImp) {
        this.cryptoKeyRepository = cryptoKeyRepository;
        this.projectServiceImp = projectServiceImp;
        this.licenseServiceImp = licenseServiceImp;
    }

    public CreateCryptoKeyDto createCryptoKey(CreateCryptoKeyDto cryptoKeyDto) {

        CryptoKey cryptoKey = new CryptoKey();
        cryptoKey.setProject(projectServiceImp.getProject(cryptoKeyDto.getProject()));
        cryptoKey.setDescription(cryptoKeyDto.getDescription());

        while (true) {
            cryptoKey.setUuid(UUID.randomUUID().toString());
            if (!cryptoKeyRepository.existsByUuid(cryptoKey.getUuid())) {
                break;
            }
        }
        cryptoKeyRepository.save(cryptoKey);
        return cryptoKeyDto;
    }

    public List<ListCryptoKeyDto> getAllCryptoKeys(int page, int number) {
        Pageable pageable = PageRequest.of(page, number);
        Page<CryptoKey> cryptoKeyPage = cryptoKeyRepository.findAll(pageable);
        List<ListCryptoKeyDto> cryptoKeyDtos = new ArrayList<>();

        for (CryptoKey cryptoKey : cryptoKeyPage.getContent()) {
            cryptoKeyDtos.add(new ListCryptoKeyDto(cryptoKey.getDescription(), cryptoKey.getProject().getName()));
        }

        return cryptoKeyDtos;
    }

    public CryptoKeyDto getCryptoKeyById(String uuid) {
        if (cryptoKeyRepository.findByUuid(uuid).isPresent()) {
            CryptoKey cryptoKey = cryptoKeyRepository.findByUuid(uuid).get();
            List<String> licenses = new ArrayList<>();
            for (int i = 0; i < cryptoKey.getLicenses().size(); i++) {
                licenses.add(cryptoKey.getLicenses().get(i).getUuid());
            }
            return new CryptoKeyDto(cryptoKey.getDescription(), cryptoKey.getProject().getName(), licenses);
        }
        throw new NotFoundException("Oops no crypto key with this id");
    }

    public CreateCryptoKeyDto updateCryptoKey(String uuid, CreateCryptoKeyDto updatedCryptoKey) {

        if (cryptoKeyRepository.findByUuid(uuid).isPresent()) {
            CryptoKey existingCryptoKey = cryptoKeyRepository.findByUuid(uuid).get();
            existingCryptoKey.setDescription(updatedCryptoKey.getDescription());
            existingCryptoKey.setProject(projectServiceImp.getProject(updatedCryptoKey.getProject()));
            List<License> licenses = new ArrayList<>();
            for (int i = 0; i < updatedCryptoKey.getLicensesId().size(); i++) {
                licenses.add(licenseServiceImp.getLicense(updatedCryptoKey.getLicensesId().get(i)));
            }
            existingCryptoKey.setLicenses(licenses);

            cryptoKeyRepository.save(existingCryptoKey);
            return updatedCryptoKey;
        }
        throw new NotFoundException("Oops no crypto key with this id");
    }

    public void deleteCryptoKey(String uuid) {
        cryptoKeyRepository.deleteByUuid(uuid);
    }

    //use in other services
    public CryptoKey getCryptoKey(String uuid) {
        if (cryptoKeyRepository.findByUuid(uuid).isPresent()) {
            return cryptoKeyRepository.findByUuid(uuid).get();
        }
        throw new NotFoundException("Oops no crypto key with this id");
    }
}
