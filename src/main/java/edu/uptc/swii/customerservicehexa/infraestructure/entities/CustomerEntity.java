package edu.uptc.swii.customerservicehexa.infraestructure.entities;

import edu.uptc.swii.customerservicehexa.domain.model.Customer;
import jakarta.persistence.*;

@Entity
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String address;

    public CustomerEntity() {
    }

    public CustomerEntity(Integer id, String firstName, String lastName, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public static CustomerEntity fromDomainModel(Customer customer) {
        return new CustomerEntity(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getAddress());
    }

    public Customer toDomainModel() {
        return new Customer(this.id, this.firstName, this.lastName, this.address);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
