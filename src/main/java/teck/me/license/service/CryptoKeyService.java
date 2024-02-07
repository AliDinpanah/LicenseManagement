package teck.me.license.service;

import teck.me.license.model.CryptoKey;
import teck.me.license.model.dto.CreateCryptoKeyDto;
import teck.me.license.model.dto.CryptoKeyDto;
import teck.me.license.model.dto.ListCryptoKeyDto;

import java.util.List;

public interface CryptoKeyService {

    List<ListCryptoKeyDto> getAllCryptoKeys(int page, int number);

    CryptoKeyDto getCryptoKeyById(String uuid);

    void deleteCryptoKey(String uuid);

    CreateCryptoKeyDto updateCryptoKey(String uuid, CreateCryptoKeyDto updatedCryptoKey);

    CreateCryptoKeyDto createCryptoKey(CreateCryptoKeyDto cryptoKeyDto);

    void saveCryptoKey(CryptoKey cryptoKey);
}
