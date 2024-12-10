package com.oop.eventticketingsystem.controller.ticket;

import com.oop.eventticketingsystem.dto.response.ResponseMessageDto;
import com.oop.eventticketingsystem.dto.response.TicketStatusResponseDto;
import com.oop.eventticketingsystem.service.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A controller for handling tickets (e.g. ticket count)
 */
@RestController
@RequestMapping("/api.eventTicket/v1/ticket")
public class TicketController {
    TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * Get the ticket count
     * @return a response entity
     */
    @GetMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketCount() {
        try {
            return ResponseEntity.ok(new TicketStatusResponseDto(
                    ticketService.getCurrentTicketCount(), ticketService.getMaxLimit())
            );
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    new ResponseMessageDto("500 Internal Server Error", e.getMessage())
            );
        }
    }
}