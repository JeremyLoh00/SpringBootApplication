package com.etiqajavatechnicalassessment.javaTechnicalAssessment.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiqajavatechnicalassessment.javaTechnicalAssessment.Exception.ResourceNotFoundException;
import com.etiqajavatechnicalassessment.javaTechnicalAssessment.model.Customer;
import com.etiqajavatechnicalassessment.javaTechnicalAssessment.service.CustomerService;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    //Get list of customer
    @GetMapping
    public List<Customer> getAllCustomers() {
    	logger.info("Getting customer list");
        List<Customer> customers = customerService.getAllCustomers();
        if (customers.isEmpty()) {
            logger.warn("No customers found");
        }
        return customers;
    }
    //Get customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
    	logger.info("Getting customer {}", id);
    	Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isPresent()) {
            logger.info("Customer found", customer.get());
            return ResponseEntity.ok(customer.get());
        } else {
            logger.error("Customer {} not found", id);
            throw new ResourceNotFoundException("Customer " + id + " not found.");
        }
    }
    //Add new customer
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
    	logger.info("Adding new customer");
    	try {
            Customer createdCustomer = customerService.createCustomer(customer);
            logger.info("Customer created", createdCustomer.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
        } catch (Exception e) {
            logger.error("Error creating customer {}", e.getMessage());
            throw e; 
        }
    }
    //Update customer data
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
    	logger.info("Updating customer {}", id);
    	Customer updatedCustomer = customerService.updateCustomer(id, customer);
        if (updatedCustomer != null) {
            logger.info("Customer updated", updatedCustomer);
            return ResponseEntity.ok(updatedCustomer);
        } else {
            logger.error("Customer {} not found for update", id);
            throw new ResourceNotFoundException("Customer " + id + " not found for update.");
        }
    }
    //Delete customer by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
    	 try {
             logger.info("Deleting customer {}", id);
             customerService.deleteCustomer(id); 
             return ResponseEntity.noContent().build();
         } catch (ResourceNotFoundException ex) {
             logger.error("Customer {} not found", id);
             throw ex; 
         } catch (Exception ex) {
             logger.error("Error deleting customer {}", id, ex);
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
         }
    }
}
