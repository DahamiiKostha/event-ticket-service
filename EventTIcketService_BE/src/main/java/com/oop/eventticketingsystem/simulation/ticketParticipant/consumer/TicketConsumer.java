package com.oop.eventticketingsystem.simulation.ticketParticipant.consumer;

import com.oop.eventticketingsystem.eventTransaction.observer.DatabaseSinker;
import com.oop.eventticketingsystem.eventTransaction.subject.DomainEventPublisher;
import com.oop.eventticketingsystem.eventTransaction.subject.Subject;
import com.oop.eventticketingsystem.model.Customer;
import com.oop.eventticketingsystem.model.Ticket;
import com.oop.eventticketingsystem.model.Transaction;
import com.oop.eventticketingsystem.simulation.ticketParticipant.AbstractTicketHandler;
import com.oop.eventticketingsystem.core.ticket.TicketPool;
import com.oop.eventticketingsystem.simulation.Timer;
import com.oop.eventticketingsystem.util.Global;
import com.oop.eventticketingsystem.util.spring.ApplicationContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A TicketConsumer is a Runnable that consumes tickets at a random interval.
 */
public class TicketConsumer extends AbstractTicketHandler implements Consumer, Runnable {
    private static final Logger log = LoggerFactory.getLogger(TicketConsumer.class);
    private final TicketPool ticketPool;
    private final Customer customer;
    private final Timer timer;
    private final Subject<Transaction> subject;

    public TicketConsumer(Timer timer,TicketPool ticketPool, Customer customer) {
        this.ticketPool = ticketPool;
        this.customer = customer;
        this.timer = timer;
        this.subject = new DomainEventPublisher<>();

        initObservers();
    }

    /**
     * Initialize the observers.
     */
    public void initObservers() {
        DatabaseSinker databaseSinker = ApplicationContextHolder.getBean(DatabaseSinker.class);
        this.subject.addObserver(databaseSinker);
    }

    /**
     * Consume a ticket from the pool.
     *
     * @return the consumed ticket
     */
    @Override
    public Ticket consume() {
        Ticket ticket = ticketPool.getTicket();
        if (ticket == null) {
            log.warn("No ticket retrieved for customer {} due to interruption.", customer.getName());
            return null;
        }
        publishTransactionEvent(ticket);
        return ticket;
    }

    /**
     * Run the consumer thread.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            long start = System.currentTimeMillis();
            try {
                // Generate a random delay between 0 and PRODUCE_TIME
                int interval = timer.getRandomDelay(Global.CONSUME_TIME);
                delayFor(interval);
                // consume a ticket
                Ticket ticket = consume();
                if(ticket == null) {
                    continue;
                }
                log.info("Customer {} | consumed ticket: {}", customer.getName(), ticket.getName());
                // Calculate the remaining time to wait
                long end = System.currentTimeMillis();
                long remainingWait = Math.max(0, Global.CONSUME_TIME - (end - start));
                // Delay for the remaining time
                delayFor(remainingWait);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Publish a transaction event.
     * @param ticket the ticket to publish
     */
    public void publishTransactionEvent(Ticket ticket) {
        Transaction transaction = new Transaction(customer, ticket, 1, ticket.getPrice());
        subject.notifyObservers(transaction);
    }
}
