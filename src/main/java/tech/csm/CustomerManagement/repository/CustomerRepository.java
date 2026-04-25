package tech.csm.CustomerManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.csm.CustomerManagement.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
