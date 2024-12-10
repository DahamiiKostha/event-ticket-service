package com.oop.eventticketingsystem.simulation.ticketParticipant.producer;

import com.oop.eventticketingsystem.eventTransaction.observer.TicketThresholdMonitor;
import com.oop.eventticketingsystem.eventTransaction.subject.DomainEventPublisher;
import com.oop.eventticketingsystem.eventTransaction.subject.Subject;
import com.oop.eventticketingsystem.model.Ticket;
import com.oop.eventticketingsystem.model.Vendor;
import com.oop.eventticketingsystem.simulation.ticketParticipant.AbstractTicketHandler;
import com.oop.eventticketingsystem.core.ticket.TicketPool;
import com.oop.eventticketingsystem.simulation.Timer;
import com.oop.eventticketingsystem.util.Global;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A TicketProducer is a Runnable that produces tickets at a random interval.
 */
public class TicketProducer extends AbstractTicketHandler implements Producer, Runnable {
    private static final Logger log = LoggerFactory.getLogger(TicketProducer.class);
    private final TicketPool ticketPool;
    private final Timer timer;
    private final Vendor vendor;
    private final Ticket ticket;
    private final Subject<Integer> subject;

    public TicketProducer(Timer timer, TicketPool ticketPool, Vendor vendor, Ticket ticket) {
        this.timer = timer;
        this.ticketPool = ticketPool;
        this.vendor = vendor;
        this.ticket = ticket;
        this.subject = new DomainEventPublisher<>();

        initObservers();
    }

    /**
     * Initialize the observers.
     */
    public void initObservers() {
        TicketThresholdMonitor ticketThresholdMonitor = new TicketThresholdMonitor();
        this.subject.addObserver(ticketThresholdMonitor);
    }

    /**
     * Produce a ticket and add it to the pool.
     */
    @Override
    public void produce() {
        ticketPool.addTicket(ticket);
        subject.notifyObservers(1);
    }

    /**
     * Run the producer thread.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            long start = System.currentTimeMillis();
            try {
                // Generate a random delay between 0 and PRODUCE_TIME
                int interval = timer.getRandomDelay(Global.PRODUCE_TIME);
                delayFor(interval);
                // Produce a ticket
                produce();
                log.info("Vendor {} | produced ticket: {}", vendor.getName(), ticket);
                // Calculate the remaining time to wait
                long end = System.currentTimeMillis();
                long remainingWait = Math.max(0, Global.PRODUCE_TIME - (end - start));
                // Delay for the remaining time
                delayFor(remainingWait);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
