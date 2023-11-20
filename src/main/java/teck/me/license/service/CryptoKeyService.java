package teck.me.license.service;

import teck.me.license.model.CryptoKey;

import java.util.List;

public interface CryptoKeyService {

    List<CryptoKey> getAllCryptoKeys();

    CryptoKey getCryptoKeyById(long id);

    CryptoKey saveCryptoKey(CryptoKey cryptoKey);

    void deleteCryptoKey(long id);

    CryptoKey updateCryptoKey(long id, CryptoKey updatedCryptoKey);
}
