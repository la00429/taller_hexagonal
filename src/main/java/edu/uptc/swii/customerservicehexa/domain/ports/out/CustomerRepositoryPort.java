package edu.uptc.swii.customerservicehexa.domain.ports.out;

import edu.uptc.swii.customerservicehexa.domain.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryPort {
    Customer save(Customer customer);
    Optional<Customer> findById(Integer id);
    List<Customer> findAll();
    Optional<Customer> update(Customer customer);
    boolean deleteById(Integer id);
}
