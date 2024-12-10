package com.oop.eventticketingsystem.core.ticket;

import com.oop.eventticketingsystem.core.Configurations.ConfigurationManager;
import com.oop.eventticketingsystem.model.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A thread-safe TicketPool using Singleton pattern.
 * Manages a bounded pool of tickets with concurrent access.
 */
public class TicketPool {
    private static final Logger log = LoggerFactory.getLogger(TicketPool.class);

    private final Queue<Ticket> queue;
    private final int Capacity;
    private final Lock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    // Private constructor for Singleton
    private TicketPool(int Capacity) {
        this.queue = new ConcurrentLinkedQueue<>();
        this.Capacity = Capacity;
        this.lock = new ReentrantLock();
        this.notFull = lock.newCondition();
        this.notEmpty = lock.newCondition();
    }

    // Lazy-loaded Singleton using nested static class
    private static class SingletonHelper {
        private static final TicketPool INSTANCE = new TicketPool(
                ConfigurationManager.getInstance().getConfigurations().getMaxTicketCapacity()
        );
    }

    /**
     * Gets the singleton instance of TicketPool.
     *
     * @return the singleton TicketPool instance
     */
    public static TicketPool getInstance() {
        return SingletonHelper.INSTANCE;
    }

    /**
     * Adds a ticket to the pool. Blocks if the pool is full.
     *
     * @param ticket the ticket to add
     */
    public synchronized void addTicket(Ticket ticket) {

        try {
            while (queue.size() >= Capacity) {
                log.info("Ticket pool is full. Waiting to add ticket.");
                wait();
            }
            if (queue.size() < Capacity) {
                queue.add(ticket);
            }
            //notEmpty.signal(); // Signal that the pool is no longer empty
            notifyAll();
        } catch (InterruptedException e) {
            log.error("Thread interrupted while adding a ticket: {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Retrieves and removes a ticket from the pool. Blocks if the pool is empty.
     *
     * @return the retrieved ticket
     */
    public synchronized Ticket getTicket() {

        try {
            while (queue.isEmpty()) {
                log.info("Ticket pool is empty. Waiting to retrieve ticket.");
                wait();
            }
            Ticket ticket = queue.poll();   //remove ticket
            notifyAll();// Signal that the pool is no longer full
            return ticket;
        } catch (InterruptedException e) {
            log.error("Thread interrupted while retrieving a ticket: {}", e.getMessage());
            Thread.currentThread().interrupt();
            return null;
        }
    }

    public synchronized void resetTicketPool() {
        queue.clear();
    }

}
