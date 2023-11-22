package teck.me.license.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teck.me.license.model.CryptoKey;

import java.util.Optional;

@Repository
public interface CryptoKeyRepository extends JpaRepository<CryptoKey,Long> {
    Optional<CryptoKey> findByUuid(String uuid);

    boolean existsByUuid(String uuid);

    void deleteByUuid(String uuid);
}
