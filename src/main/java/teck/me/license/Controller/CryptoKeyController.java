package teck.me.license.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teck.me.license.model.dto.CreateCryptoKeyDto;
import teck.me.license.model.dto.CryptoKeyDto;
import teck.me.license.model.dto.ListCryptoKeyDto;
import teck.me.license.service.CryptoKeyService;

import java.util.List;

@RestController
@RequestMapping("/api/crypto-keys")
public class CryptoKeyController {
    private final CryptoKeyService cryptoKeyService;

    public CryptoKeyController(CryptoKeyService cryptoKeyService) {
        this.cryptoKeyService = cryptoKeyService;
    }

    @PostMapping
    private ResponseEntity<CreateCryptoKeyDto> createCryptoKey(@RequestBody CreateCryptoKeyDto cryptoKeyDto) {
        return new ResponseEntity<>(cryptoKeyService.createCryptoKey(cryptoKeyDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ListCryptoKeyDto>> getAllCryptoKeys(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int number){

        List<ListCryptoKeyDto> cryptoKeys = cryptoKeyService.getAllCryptoKeys(page, number);
        return new ResponseEntity<>(cryptoKeys, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<CryptoKeyDto> getCryptoKeyById(@PathVariable String uuid) {
        CryptoKeyDto cryptoKey = cryptoKeyService.getCryptoKeyById(uuid);
            return new ResponseEntity<>(cryptoKey, HttpStatus.OK);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<CreateCryptoKeyDto> updateCryptoKey(@PathVariable String uuid, @RequestBody CreateCryptoKeyDto updatedCryptoKey) {
        CreateCryptoKeyDto updatedKey = cryptoKeyService.updateCryptoKey(uuid, updatedCryptoKey);
            return new ResponseEntity<>(updatedKey, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteCryptoKey(@PathVariable String uuid) {
        cryptoKeyService.deleteCryptoKey(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
