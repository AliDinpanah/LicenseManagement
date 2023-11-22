package teck.me.license.service;

import teck.me.license.model.CryptoKey;
import teck.me.license.model.dto.CryptoKeyDto;

import java.util.List;

public interface CryptoKeyService {

    List<CryptoKeyDto> getAllCryptoKeys();

    CryptoKeyDto getCryptoKeyById(String uuid);

    CryptoKeyDto saveCryptoKey(CryptoKeyDto cryptoKeyDto);

    void deleteCryptoKey(String uuid);

    CryptoKey updateCryptoKey(String uuid, CryptoKeyDto updatedCryptoKey);
}
