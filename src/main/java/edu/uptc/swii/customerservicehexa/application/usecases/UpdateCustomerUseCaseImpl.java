package edu.uptc.swii.customerservicehexa.application.usecases;

import edu.uptc.swii.customerservicehexa.domain.model.Customer;
import edu.uptc.swii.customerservicehexa.domain.ports.in.UpdateCustomerUseCase;
import edu.uptc.swii.customerservicehexa.domain.ports.out.CustomerRepositoryPort;

import java.util.Optional;

public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    public UpdateCustomerUseCaseImpl(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public Optional<Customer> updateCustomer(Integer id, Customer updateCustomer) {
        updateCustomer.setId(id);
        return customerRepositoryPort.update(updateCustomer);
    }
}
