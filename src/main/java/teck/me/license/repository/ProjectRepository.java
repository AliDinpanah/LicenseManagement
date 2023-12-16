package teck.me.license.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teck.me.license.model.CryptoKey;
import teck.me.license.model.Project;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    Optional<CryptoKey> findByCryptoKeys_Uuid(String uuid);

    boolean existsByName(String name);
}
