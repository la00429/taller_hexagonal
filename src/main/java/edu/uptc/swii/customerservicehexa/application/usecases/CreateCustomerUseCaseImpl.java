package edu.uptc.swii.customerservicehexa.application.usecases;

import edu.uptc.swii.customerservicehexa.domain.model.Customer;
import edu.uptc.swii.customerservicehexa.domain.ports.in.CreateCustomerUseCase;
import edu.uptc.swii.customerservicehexa.domain.ports.out.CustomerRepositoryPort;

public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    public CreateCustomerUseCaseImpl(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public Customer CreateCustomer(Customer customer) {
        return customerRepositoryPort.save(customer);
    }
}
