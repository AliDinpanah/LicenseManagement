package teck.me.license.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teck.me.license.model.CryptoKey;

@Repository
public interface CryptoKeyRepository extends JpaRepository<CryptoKey,Long> {

}
