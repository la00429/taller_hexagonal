package edu.uptc.swii.customerservicehexa.infraestructure.repositories;

import edu.uptc.swii.customerservicehexa.domain.model.Customer;
import edu.uptc.swii.customerservicehexa.domain.ports.out.CustomerRepositoryPort;
import edu.uptc.swii.customerservicehexa.infraestructure.entities.CustomerEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaCustomerRepositoryAdapter implements CustomerRepositoryPort {

    private final JpaCustomerRepository jpaCustomerRepository;

    public JpaCustomerRepositoryAdapter(JpaCustomerRepository jpaCustomerRepository) {
        this.jpaCustomerRepository = jpaCustomerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity customerEntity = CustomerEntity.fromDomainModel(customer);
        CustomerEntity savedCustomerEntity = jpaCustomerRepository.save(customerEntity);
        return savedCustomerEntity.toDomainModel();
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return jpaCustomerRepository.findById(id).map(CustomerEntity::toDomainModel);
    }

    @Override
    public List<Customer> findAll() {
        return jpaCustomerRepository.findAll().stream()
                .map(CustomerEntity::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Customer> update(Customer customer) {
        if (jpaCustomerRepository.existsById(customer.getId())) {
            CustomerEntity customerEntity = CustomerEntity.fromDomainModel(customer);
            CustomerEntity updatedCustomerEntity = jpaCustomerRepository.save(customerEntity);
            return Optional.of(updatedCustomerEntity.toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Integer id) {
        if (jpaCustomerRepository.existsById(id)) {
            jpaCustomerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
