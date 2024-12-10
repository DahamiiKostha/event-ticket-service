package com.oop.eventticketingsystem.service.ticket;

import com.oop.eventticketingsystem.model.Ticket;
import com.oop.eventticketingsystem.repository.TicketRepository;
import com.oop.eventticketingsystem.service.ticketLimiter.TicketCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A class for TicketService
 */
@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketCounter ticketCounter = TicketCounter.getInstance();

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getTicketsLimited() {
        return ticketRepository.findAll(PageRequest.of(0, 5)).getContent();
    }

    public void saveTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public int getCurrentTicketCount() {
        return ticketCounter.getTicketCount();
    }

    public int getMaxLimit() {
        return ticketCounter.getMaxLimit();
    }
}
