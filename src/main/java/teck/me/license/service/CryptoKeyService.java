package teck.me.license.service;

import teck.me.license.model.dto.CryptoKeyDto;
import teck.me.license.model.dto.ListCryptoKeyDto;

import java.util.List;

public interface CryptoKeyService {

    List<ListCryptoKeyDto> getAllCryptoKeys(int page, int number) throws IllegalAccessException;

    CryptoKeyDto getCryptoKeyById(String uuid) throws IllegalAccessException;

    void deleteCryptoKey(String uuid);

    ListCryptoKeyDto updateCryptoKey(String uuid, ListCryptoKeyDto updatedCryptoKey) throws IllegalAccessException;

    ListCryptoKeyDto createCryptoKey(ListCryptoKeyDto cryptoKeyDto) throws IllegalAccessException;
}
