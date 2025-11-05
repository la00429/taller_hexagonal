package edu.uptc.swii.customerservicehexa.infraestructure.controllers;

import edu.uptc.swii.customerservicehexa.application.services.CustomerService;
import edu.uptc.swii.customerservicehexa.domain.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/addcustomer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer created = customerService.CreateCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer customerId) {
        return customerService.getCustomerById(customerId)
                .map(customer -> ResponseEntity.status(HttpStatus.OK).body(customer))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/allcustomers")
    public ResponseEntity<List<Customer>> findAllCustomers() {
        List<Customer> customers = customerService.findAllCustomers();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer customerId, @RequestBody Customer updateCustomer) {
        return customerService.updateCustomer(customerId, updateCustomer)
                .map(customer -> ResponseEntity.status(HttpStatus.OK).body(customer))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Integer customerId) {
        if (customerService.deleteCustomer(customerId)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
