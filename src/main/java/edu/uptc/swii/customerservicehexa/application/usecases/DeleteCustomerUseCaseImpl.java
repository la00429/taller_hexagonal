package edu.uptc.swii.customerservicehexa.application.usecases;

import edu.uptc.swii.customerservicehexa.domain.ports.in.DeleteCustomerUseCase;
import edu.uptc.swii.customerservicehexa.domain.ports.out.CustomerRepositoryPort;

public class DeleteCustomerUseCaseImpl implements DeleteCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    public DeleteCustomerUseCaseImpl(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public boolean deleteCustomer(Integer id) {
        return customerRepositoryPort.deleteById(id);
    }
}
