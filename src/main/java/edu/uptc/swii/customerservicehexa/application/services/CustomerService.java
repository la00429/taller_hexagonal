package edu.uptc.swii.customerservicehexa.application.services;

import edu.uptc.swii.customerservicehexa.domain.model.Customer;
import edu.uptc.swii.customerservicehexa.domain.ports.in.CreateCustomerUseCase;
import edu.uptc.swii.customerservicehexa.domain.ports.in.DeleteCustomerUseCase;
import edu.uptc.swii.customerservicehexa.domain.ports.in.FindCustomersUseCase;
import edu.uptc.swii.customerservicehexa.domain.ports.in.UpdateCustomerUseCase;

import java.util.List;
import java.util.Optional;

public class CustomerService implements CreateCustomerUseCase, DeleteCustomerUseCase, FindCustomersUseCase, UpdateCustomerUseCase {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final FindCustomersUseCase findCustomersUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;

    public CustomerService(CreateCustomerUseCase createCustomerUseCase, DeleteCustomerUseCase deleteCustomerUseCase, FindCustomersUseCase findCustomersUseCase, UpdateCustomerUseCase updateCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
        this.findCustomersUseCase = findCustomersUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
    }

    @Override
    public Customer CreateCustomer(Customer customer) {
        return createCustomerUseCase.CreateCustomer(customer);
    }

    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        return findCustomersUseCase.getCustomerById(id);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return findCustomersUseCase.findAllCustomers();
    }

    @Override
    public Optional<Customer> updateCustomer(Integer id, Customer updateCustomer) {
        return updateCustomerUseCase.updateCustomer(id, updateCustomer);
    }

    @Override
    public boolean deleteCustomer(Integer id) {
        return deleteCustomerUseCase.deleteCustomer(id);
    }
}
