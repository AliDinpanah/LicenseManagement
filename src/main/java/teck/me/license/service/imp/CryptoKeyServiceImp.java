package teck.me.license.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teck.me.license.model.CryptoKey;
import teck.me.license.repository.CryptoKeyRepository;
import teck.me.license.service.CryptoKeyService;

import java.util.List;

@Service
public class CryptoKeyServiceImp implements CryptoKeyService {
    @Autowired
    private CryptoKeyRepository cryptoKeyRepository;

    public List<CryptoKey> getAllCryptoKeys() {
        return cryptoKeyRepository.findAll();
    }

    public CryptoKey getCryptoKeyById(long id) {
        return cryptoKeyRepository.findById(id).orElse(null);
    }

    public CryptoKey saveCryptoKey(CryptoKey cryptoKey) {
        return cryptoKeyRepository.save(cryptoKey);
    }

    public CryptoKey updateCryptoKey(long id, CryptoKey updatedCryptoKey) {
        CryptoKey existingCryptoKey = cryptoKeyRepository.findById(id).get();
        if (existingCryptoKey != null) {
            // Update fields as needed

            return cryptoKeyRepository.save(existingCryptoKey);
        }
        return null; // Handle not found scenario
    }

    public void deleteCryptoKey(long id) {
        cryptoKeyRepository.deleteById(id);
    }
}
