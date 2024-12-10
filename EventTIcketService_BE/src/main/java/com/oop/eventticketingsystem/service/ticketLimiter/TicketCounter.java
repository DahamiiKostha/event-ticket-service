package com.oop.eventticketingsystem.service.ticketLimiter;

import com.oop.eventticketingsystem.core.Configurations.ConfigurationManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A class for TicketCounter
 */
public class TicketCounter {
    private final AtomicInteger ticketCount; // Thread-safe ticket counter
    private volatile int maxLimit; // Maximum ticket limit

    private TicketCounter(int maxLimit) {
        this.ticketCount = new AtomicInteger(0);
        this.maxLimit = maxLimit;
    }


    // This is a thread-safe way to create a singleton class in Java
    private static class SingletonHolder {
        private static final TicketCounter INSTANCE = new TicketCounter(
                ConfigurationManager.getInstance().getConfigurations().getTotalTickets()
        );
    }

    public static TicketCounter getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public boolean isBelowLimit() {
        return ticketCount.get() < maxLimit;
    }

    public void increment() {
        ticketCount.incrementAndGet();
    }

    public int getTicketCount() {
        return ticketCount.get();
    }

    public int getMaxLimit() {
        return maxLimit;
    }

    public void resetTicketCount() {
        ticketCount.set(0);
    }

    public void setMaxLimit(int maxLimit) {
        this.maxLimit = maxLimit;
    }
}
