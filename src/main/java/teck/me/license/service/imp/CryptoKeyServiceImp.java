package teck.me.license.service.imp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
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

    private final CryptoKeyRepository cryptoKeyRepository;

    private Converter<License, LicenseDto> convertLicenseToDto;
    private Converter<LicenseDto, License> convertDtoToLicense;
    private Converter<Project, ProjectDto> convertProjectToDto;
    private Converter<ProjectDto, Project> convertDtoToProject;

    public CryptoKeyServiceImp(CryptoKeyRepository cryptoKeyRepository) {
        this.cryptoKeyRepository = cryptoKeyRepository;
    }

    public ListCryptoKeyDto createCryptoKey(ListCryptoKeyDto cryptoKeyDto) throws IllegalAccessException {

        CryptoKey cryptoKey = new CryptoKey();
        cryptoKey.setProject(convertDtoToProject.convert(cryptoKeyDto.getProject(),new Project()));
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

    public List<ListCryptoKeyDto> getAllCryptoKeys(int page, int number) throws IllegalAccessException {
        Pageable pageable = PageRequest.of(page, number);
        Page<CryptoKey> cryptoKeyPage = cryptoKeyRepository.findAll(pageable);
        List<ListCryptoKeyDto> cryptoKeyDtos = new ArrayList<>();

        for (CryptoKey cryptoKey : cryptoKeyPage.getContent()) {
            cryptoKeyDtos.add(new ListCryptoKeyDto(cryptoKey.getDescription(), convertProjectToDto.convert(cryptoKey.getProject(),new ProjectDto())));
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

    public ListCryptoKeyDto updateCryptoKey(String uuid, ListCryptoKeyDto updatedCryptoKey) throws IllegalAccessException {

        if (cryptoKeyRepository.existsByUuid(uuid)) {
            CryptoKey existingCryptoKey = cryptoKeyRepository.findByUuid(uuid).get();
            existingCryptoKey.setDescription(updatedCryptoKey.getDescription());
            existingCryptoKey.setProject(convertDtoToProject.convert(updatedCryptoKey.getProject(),new Project()));

            cryptoKeyRepository.save(existingCryptoKey);
            return updatedCryptoKey;
        }
        throw new NotFoundException("Oops no crypto key with this id");
    }

    public void deleteCryptoKey(String uuid) {
        cryptoKeyRepository.deleteByUuid(uuid);
    }
}
