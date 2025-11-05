package edu.uptc.swii.customerservicehexa.domain.ports.in;

import edu.uptc.swii.customerservicehexa.domain.model.Customer;
import java.util.Optional;

public interface UpdateCustomerUseCase {
    Optional<Customer> updateCustomer(Integer id, Customer updateCustomer);
}
