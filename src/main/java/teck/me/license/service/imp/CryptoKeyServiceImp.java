package teck.me.license.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teck.me.license.model.CryptoKey;
import teck.me.license.model.dto.CryptoKeyDto;
import teck.me.license.repository.CryptoKeyRepository;
import teck.me.license.service.CryptoKeyService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CryptoKeyServiceImp implements CryptoKeyService {
    @Autowired
    private CryptoKeyRepository cryptoKeyRepository;

    public List<CryptoKeyDto> getAllCryptoKeys() {
        List<CryptoKey> cryptoKeys = cryptoKeyRepository.findAll();
        List<CryptoKeyDto> cryptoKeyDtos = new ArrayList<>();
        for (CryptoKey cryptoKey:cryptoKeys){
            cryptoKeyDtos.add(new CryptoKeyDto(cryptoKey.getDescription(), cryptoKey.getProject(), cryptoKey.getLicenses()));
        }
        return cryptoKeyDtos;
    }

    public CryptoKeyDto getCryptoKeyById(String uuid) {
        CryptoKey cryptoKey = cryptoKeyRepository.findByUuid(uuid).get();
        CryptoKeyDto cryptoKeyDto=new CryptoKeyDto(cryptoKey.getDescription(), cryptoKey.getProject(), cryptoKey.getLicenses());
        return cryptoKeyDto;
    }

    public CryptoKeyDto saveCryptoKey(CryptoKeyDto cryptoKeyDto) {
        CryptoKey cryptoKey=new CryptoKey();
        while (true){
            cryptoKey.setUuid(UUID.randomUUID().toString());
            if(!cryptoKeyRepository.existsByUuid(cryptoKey.getUuid())){
                break;
            }
        }
        cryptoKey.setLicenses(cryptoKeyDto.getLicenses());
        cryptoKey.setDescription(cryptoKeyDto.getDescription());
        cryptoKey.setProject(cryptoKeyDto.getProject());
        cryptoKeyRepository.save(cryptoKey);
        return cryptoKeyDto;
    }

    public CryptoKey updateCryptoKey(String uuid, CryptoKeyDto updatedCryptoKey) {
        CryptoKey existingCryptoKey = cryptoKeyRepository.findByUuid(uuid).get();
        if (existingCryptoKey != null) {
            // Update fields as needed

            return cryptoKeyRepository.save(existingCryptoKey);
        }
        return null; // Handle not found scenario
    }

    public void deleteCryptoKey(String uuid) {
        cryptoKeyRepository.deleteByUuid(uuid);
    }
}
