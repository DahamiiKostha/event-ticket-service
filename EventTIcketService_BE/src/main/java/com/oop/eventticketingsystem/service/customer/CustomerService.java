package com.oop.eventticketingsystem.service.customer;

import com.oop.eventticketingsystem.model.Customer;
import com.oop.eventticketingsystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A class for CustomerService
 */
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    // Get all customers
    public List<Customer> getCustomersLimited() {
        return customerRepository.findAll(PageRequest.of(0, 10)).getContent();
    }
}
