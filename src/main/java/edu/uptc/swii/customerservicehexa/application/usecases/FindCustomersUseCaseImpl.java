package edu.uptc.swii.customerservicehexa.application.usecases;

import edu.uptc.swii.customerservicehexa.domain.model.Customer;
import edu.uptc.swii.customerservicehexa.domain.ports.in.FindCustomersUseCase;
import edu.uptc.swii.customerservicehexa.domain.ports.out.CustomerRepositoryPort;

import java.util.List;
import java.util.Optional;

public class FindCustomersUseCaseImpl implements FindCustomersUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    public FindCustomersUseCaseImpl(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        return customerRepositoryPort.findById(id);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepositoryPort.findAll();
    }
}
