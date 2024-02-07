package teck.me.license.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teck.me.license.model.CryptoKey;
import teck.me.license.model.Project;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByCryptoKeysUuid(String cryptoKeyUuid);

    boolean existsByName(String name);

    Optional<Project> findByName(String name);

    void deleteByName(String name);
}
