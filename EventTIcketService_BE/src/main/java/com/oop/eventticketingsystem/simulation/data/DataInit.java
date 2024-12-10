package com.oop.eventticketingsystem.simulation.data;

import com.oop.eventticketingsystem.model.Customer;
import com.oop.eventticketingsystem.model.Ticket;
import com.oop.eventticketingsystem.model.Vendor;
import com.oop.eventticketingsystem.simulation.data.dbDataProvider.DataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Initialize/ populate the data
 */
@Component
public class DataInit {
    private final DataProvider<Customer> customerFactory;
    private final DataProvider<Vendor> vendorFactory;
    private final DataProvider<Ticket> ticketFactory;

    @Autowired
    public DataInit(DataProvider<Customer> customerFactory, DataProvider<Vendor> vendorFactory, DataProvider<Ticket> ticketFactory) {
        this.customerFactory = customerFactory;
        this.vendorFactory = vendorFactory;
        this.ticketFactory = ticketFactory;
    }

    /**
     * Initialize the data
     */
    public void init() {
        customerFactory.populate();
        vendorFactory.populate();
        ticketFactory.populate();
    }
}
