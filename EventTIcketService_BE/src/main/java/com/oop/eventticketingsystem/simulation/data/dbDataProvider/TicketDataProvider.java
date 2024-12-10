package com.oop.eventticketingsystem.simulation.data.dbDataProvider;

import com.oop.eventticketingsystem.model.Ticket;
import com.oop.eventticketingsystem.service.ticket.TicketService;
import com.oop.eventticketingsystem.simulation.data.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for TicketDataProvider from Db
 */
@Component
public class TicketDataProvider implements DataProvider<Ticket> {
    private final TicketService ticketService;
    private List<Ticket> tickets = new ArrayList<>();
    private final DataStore dataStore = DataStore.getInstance();

    @Autowired
    public TicketDataProvider(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * Populate the data provider with tickets
     * @return the list of tickets
     */
    @Override
    public List<Ticket> populate() {
        if (tickets.isEmpty()) {
            // Check if the tickets are already cached in the data store
            List<Ticket> existingTickets = dataStore.getTickets();
            if (!existingTickets.isEmpty()) {
                tickets = new ArrayList<>(existingTickets);
                return tickets;
            }

            // Retrieve tickets from the database
            tickets = new ArrayList<>(ticketService.getTicketsLimited());
            if (tickets.isEmpty()) {
                throw new IllegalStateException("No tickets found in the database. Please ensure the database is populated.");
            }

            // Cache the retrieved tickets in the data store
            dataStore.setTickets(tickets);
        }
        return tickets;
    }
}
