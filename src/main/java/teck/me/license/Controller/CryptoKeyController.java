package teck.me.license.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teck.me.license.model.CryptoKey;
import teck.me.license.model.dto.CryptoKeyDto;
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

    @GetMapping
    public ResponseEntity<List<CryptoKeyDto>> getAllCryptoKeys() {
        List<CryptoKeyDto> cryptoKeys = cryptoKeyService.getAllCryptoKeys();
        return new ResponseEntity<>(cryptoKeys, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<CryptoKeyDto> getCryptoKeyById(@PathVariable String uuid) {
        CryptoKeyDto cryptoKey = cryptoKeyService.getCryptoKeyById(uuid);
        if (cryptoKey != null) {
            return new ResponseEntity<>(cryptoKey, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CryptoKeyDto> saveCryptoKey(@RequestBody CryptoKeyDto cryptoKeyDto) {
        CryptoKeyDto savedCryptoKey = cryptoKeyService.saveCryptoKey(cryptoKeyDto);
        return new ResponseEntity<>(savedCryptoKey, HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<CryptoKey> updateCryptoKey(@PathVariable String uuid, @RequestBody CryptoKeyDto updatedCryptoKey) {
        CryptoKey updatedKey = cryptoKeyService.updateCryptoKey(uuid, updatedCryptoKey);
        if (updatedKey != null) {
            return new ResponseEntity<>(updatedKey, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteCryptoKey(@PathVariable String uuid) {
        cryptoKeyService.deleteCryptoKey(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
