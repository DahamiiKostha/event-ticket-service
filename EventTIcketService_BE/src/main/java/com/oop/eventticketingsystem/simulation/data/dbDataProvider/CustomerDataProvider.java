package com.oop.eventticketingsystem.simulation.data.dbDataProvider;

import com.oop.eventticketingsystem.model.Customer;
import com.oop.eventticketingsystem.service.customer.CustomerService;
import com.oop.eventticketingsystem.simulation.data.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for CustomerDataProvider from Db
 */
@Component
public class CustomerDataProvider implements DataProvider<Customer> {
    private final CustomerService customerService;
    private List<Customer> customers = new ArrayList<>();
    private final DataStore dataStore = DataStore.getInstance();

    @Autowired
    public CustomerDataProvider(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Populate the data provider with customers
     * @return the list of customers
     */
    @Override
    public List<Customer> populate() {
        if (customers.isEmpty()) {
            // Check if the customers are already cached in the data store
            List<Customer> existingCustomers = dataStore.getCustomers();
            if (!existingCustomers.isEmpty()) {
                customers = new ArrayList<>(existingCustomers);
                return customers;
            }

            // Retrieve customers from the database
            customers = new ArrayList<>(customerService.getCustomersLimited());
            if (customers.isEmpty()) {
                throw new IllegalStateException("No customers found in the database. Please ensure the database is populated.");
            }

            // Cache the retrieved customers in the data store
            dataStore.setCustomers(customers);
        }
        return customers;
    }
}
