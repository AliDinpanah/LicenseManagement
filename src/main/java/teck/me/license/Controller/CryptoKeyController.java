package teck.me.license.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teck.me.license.model.CryptoKey;
import teck.me.license.model.dto.CryptoKeyDto;
import teck.me.license.model.dto.ListCryptoKeyDto;
import teck.me.license.service.CryptoKeyService;

import java.util.List;

@RestController
@RequestMapping("/api/crypto-keys")
public class CryptoKeyController {
    private final CryptoKeyService cryptoKeyService;

    @Autowired
    public CryptoKeyController(CryptoKeyService cryptoKeyService) {
        this.cryptoKeyService = cryptoKeyService;
    }

    @PutMapping
    private ResponseEntity<CryptoKeyDto> createCryptoKey(@RequestBody CryptoKeyDto cryptoKeyDto) {
        return new ResponseEntity<>(cryptoKeyService.createCryptoKey(cryptoKeyDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ListCryptoKeyDto>> getAllCryptoKeys(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int number) {

        List<ListCryptoKeyDto> cryptoKeys = cryptoKeyService.getAllCryptoKeys(page, number);
        return new ResponseEntity<>(cryptoKeys, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ListCryptoKeyDto> getCryptoKeyById(@PathVariable String uuid) {
        ListCryptoKeyDto cryptoKey = cryptoKeyService.getCryptoKeyById(uuid);
            return new ResponseEntity<>(cryptoKey, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CryptoKeyDto> saveCryptoKey(@RequestBody CryptoKeyDto cryptoKeyDto) {
        CryptoKeyDto savedCryptoKey = cryptoKeyService.saveCryptoKey(cryptoKeyDto);
        return new ResponseEntity<>(savedCryptoKey, HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<CryptoKey> updateCryptoKey(@PathVariable String uuid, @RequestBody CryptoKeyDto updatedCryptoKey) {
        CryptoKey updatedKey = cryptoKeyService.updateCryptoKey(uuid, updatedCryptoKey);
            return new ResponseEntity<>(updatedKey, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteCryptoKey(@PathVariable String uuid) {
        cryptoKeyService.deleteCryptoKey(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
