package com.oop.eventticketingsystem.service.ticketLimiter;

import com.oop.eventticketingsystem.simulation.ticketParticipant.ThreadHandler;
import com.oop.eventticketingsystem.util.prompt.PromptLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class for TicketCountLimiter
 */
public class TicketCountLimiter implements Runnable{
    private static final Logger log = LoggerFactory.getLogger(TicketCountLimiter.class);
    private final TicketCounter ticketCounter = TicketCounter.getInstance();
    private final PromptLogger logger = PromptLogger.getInstance();
    private final ThreadHandler threadHandler;

    public TicketCountLimiter(ThreadHandler threadHandler) {
        this.threadHandler = threadHandler;
    }

    // Run the thread
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (!ticketCounter.isBelowLimit()) {
                log.warn("Ticket count has reached the total limit");
                logger.warn("Ticket count has reached the total limit");
                stopSimulation();
                break;
            }
            try {
                Thread.sleep(800); // Sleep for 800 millisecond before checking again
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("TicketCountLimiter thread was interrupted {}", e.getMessage());
            }
        }
    }

    // Stop the simulation
    private void stopSimulation(){
        logger.fatal("Ticket count has reached the total ticket limit. Stopping the simulation...");
        this.threadHandler.stopAll();
    }
}
