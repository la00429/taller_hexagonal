package edu.uptc.swii.customerservicehexa.domain.ports.in;

import edu.uptc.swii.customerservicehexa.domain.model.Customer;
import java.util.List;
import java.util.Optional;

public interface FindCustomersUseCase {
    Optional<Customer> getCustomerById(Integer id);
    List<Customer> findAllCustomers();
}
