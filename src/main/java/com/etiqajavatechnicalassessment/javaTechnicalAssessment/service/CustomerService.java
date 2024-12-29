package com.etiqajavatechnicalassessment.javaTechnicalAssessment.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.etiqajavatechnicalassessment.javaTechnicalAssessment.model.Customer;
import com.etiqajavatechnicalassessment.javaTechnicalAssessment.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    //Get all data
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    //Get customer data by ID
    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }
    //Add new customer
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    //Update data
    public Customer updateCustomer(String id, Customer customer) {
        if (customerRepository.existsById(id)) {
            customer.setId(id);
            return customerRepository.save(customer);
        }
        return null;
    }
    //Delete data
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }
}