package teck.me.license.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teck.me.license.model.License;

@Repository
public interface LicenseRepository extends JpaRepository<License,Long> {
}
