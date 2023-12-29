package teck.me.license.service;

import teck.me.license.exception.NotFoundException;
import teck.me.license.model.CryptoKey;
import teck.me.license.model.dto.CryptoKeyDto;
import teck.me.license.model.dto.ListCryptoKeyDto;

import java.util.List;

public interface CryptoKeyService {

    List<ListCryptoKeyDto> getAllCryptoKeys(int page, int number);

    CryptoKeyDto getCryptoKeyById(String uuid) throws IllegalAccessException;

    void deleteCryptoKey(String uuid);

    CryptoKey updateCryptoKey(String uuid, CryptoKeyDto updatedCryptoKey);

    CryptoKeyDto createCryptoKey(CryptoKeyDto cryptoKeyDto) throws IllegalAccessException;
}
