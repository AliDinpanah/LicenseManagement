package teck.me.license.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teck.me.license.model.License;

import java.util.Optional;

@Repository
public interface LicenseRepository extends JpaRepository<License,Long> {

    void deleteByUuid(String uuid);

    Optional<License> findByUuid(String uuid);
}
