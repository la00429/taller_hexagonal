package edu.uptc.swii.customerservicehexa.domain.ports.in;

import edu.uptc.swii.customerservicehexa.domain.model.Customer;

public interface CreateCustomerUseCase {
    Customer CreateCustomer(Customer customer);
}
