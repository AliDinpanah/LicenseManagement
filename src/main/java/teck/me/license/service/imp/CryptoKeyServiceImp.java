package teck.me.license.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import teck.me.license.exception.DataLogicException;
import teck.me.license.exception.NotFoundException;
import teck.me.license.model.CryptoKey;
import teck.me.license.model.License;
import teck.me.license.model.Project;
import teck.me.license.model.dto.CryptoKeyDto;
import teck.me.license.model.dto.LicenseDto;
import teck.me.license.model.dto.ListCryptoKeyDto;
import teck.me.license.model.dto.ProjectDto;
import teck.me.license.repository.CryptoKeyRepository;
import teck.me.license.service.CryptoKeyService;
import org.springframework.data.domain.Pageable;
import teck.me.license.utill.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CryptoKeyServiceImp implements CryptoKeyService {
    @Autowired
    private CryptoKeyRepository cryptoKeyRepository;

    private Converter<License, LicenseDto> convertLicenseToDto;
    private Converter<LicenseDto, License> convertDtoToLicense;
    private Converter<Project, ProjectDto> convertProjectToDto;
    private Converter<ProjectDto, Project> convertDtoToProject;

    public CryptoKeyDto createCryptoKey(CryptoKeyDto cryptoKeyDto) throws IllegalAccessException {

        CryptoKey cryptoKey = new CryptoKey();
        cryptoKey.setProject(convertDtoToProject.convert(cryptoKeyDto.getProject(),new Project()));
        cryptoKey.setDescription(cryptoKeyDto.getDescription());
        cryptoKey.setLicenses(convertDtoToLicense.convertList(cryptoKeyDto.getLicenses(),new License()));

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
            cryptoKeyDtos.add(new ListCryptoKeyDto(cryptoKey.getDescription(), cryptoKey.getProject()));
        }

        return cryptoKeyDtos;
    }

    public CryptoKeyDto getCryptoKeyById(String uuid) throws IllegalAccessException {
        if (cryptoKeyRepository.existsByUuid(uuid)) {
            CryptoKey cryptoKey = cryptoKeyRepository.findByUuid(uuid).get();
            CryptoKeyDto cryptoKeyDto = new CryptoKeyDto(cryptoKey.getDescription(), convertProjectToDto.convert(cryptoKey.getProject(),new ProjectDto()),convertLicenseToDto.convertList(cryptoKey.getLicenses(),new LicenseDto()));
            return cryptoKeyDto;
        }
        throw new NotFoundException("Oops no crypto key with this id");
    }

    public CryptoKey updateCryptoKey(String uuid, CryptoKeyDto updatedCryptoKey) {

        if (cryptoKeyRepository.existsByUuid(uuid)) {
            CryptoKey existingCryptoKey = cryptoKeyRepository.findByUuid(uuid).get();
            // Update fields as needed

            return cryptoKeyRepository.save(existingCryptoKey);
        }
        throw new NotFoundException("Oops no crypto key with this id");
    }

    public void deleteCryptoKey(String uuid) {
        cryptoKeyRepository.deleteByUuid(uuid);
    }
}
