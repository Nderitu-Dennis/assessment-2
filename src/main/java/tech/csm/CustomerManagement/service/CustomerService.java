package tech.csm.CustomerManagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.csm.CustomerManagement.entity.Customer;
import tech.csm.CustomerManagement.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CustomerService {
    private final CustomerRepository customerRepo;


    public Customer save(Customer c) {
        return customerRepo.save(c);
    }

    public List<Customer> getAll() {
        return customerRepo.findAll();
    }

    public Optional<Customer> getById(Integer custId) {
        return customerRepo.findById(custId);
    }

    public void delete(Integer custId) {
        customerRepo.deleteById(custId); }
}
