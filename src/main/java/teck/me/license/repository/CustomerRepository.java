package teck.me.license.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teck.me.license.model.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    boolean existsByName(String name);

    Optional<Customer> findByName(String name);

    void deleteByName(String name);
}
