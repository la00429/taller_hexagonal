package edu.uptc.swii.customerservicehexa.infraestructure.repositories;

import edu.uptc.swii.customerservicehexa.infraestructure.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCustomerRepository extends JpaRepository<CustomerEntity, Integer> {
}
