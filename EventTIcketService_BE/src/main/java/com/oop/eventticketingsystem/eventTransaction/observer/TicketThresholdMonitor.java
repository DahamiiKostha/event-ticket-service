package com.oop.eventticketingsystem.eventTransaction.observer;

import com.oop.eventticketingsystem.service.ticketLimiter.TicketCounter;

/**
 * A class for monitoring ticket threshold
 */
public class TicketThresholdMonitor implements DomainEventObserver<Integer> {
    private final TicketCounter ticketCounter = TicketCounter.getInstance();

    @Override
    public void onDomainEvent(Integer domainObject) {
        ticketCounter.increment();
    }
}
