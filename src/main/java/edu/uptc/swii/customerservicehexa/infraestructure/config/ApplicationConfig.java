package edu.uptc.swii.customerservicehexa.infraestructure.config;

import edu.uptc.swii.customerservicehexa.application.services.CustomerService;
import edu.uptc.swii.customerservicehexa.application.usecases.CreateCustomerUseCaseImpl;
import edu.uptc.swii.customerservicehexa.application.usecases.DeleteCustomerUseCaseImpl;
import edu.uptc.swii.customerservicehexa.application.usecases.FindCustomersUseCaseImpl;
import edu.uptc.swii.customerservicehexa.application.usecases.UpdateCustomerUseCaseImpl;
import edu.uptc.swii.customerservicehexa.domain.ports.out.CustomerRepositoryPort;
import edu.uptc.swii.customerservicehexa.infraestructure.repositories.JpaCustomerRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public CustomerService customerService(CustomerRepositoryPort customerRepositoryPort) {
        return new CustomerService(
                new CreateCustomerUseCaseImpl(customerRepositoryPort),
                new DeleteCustomerUseCaseImpl(customerRepositoryPort),
                new FindCustomersUseCaseImpl(customerRepositoryPort),
                new UpdateCustomerUseCaseImpl(customerRepositoryPort)
        );
    }

    @Bean
    public CustomerRepositoryPort customerRepositoryPort(JpaCustomerRepositoryAdapter jpaCustomerRepositoryAdapter) {
        return jpaCustomerRepositoryAdapter;
    }
}
