package com.oop.eventticketingsystem.core.Configurations;

import com.oop.eventticketingsystem.model.UserConfigurations;
import com.oop.eventticketingsystem.service.ticketLimiter.TicketCounter;
import com.oop.eventticketingsystem.core.ticket.TicketPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles updating dependent systems (e.g., TicketCounter, TicketPool) based on configuration changes.
 */
public class ConfigurationUpdater {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationUpdater.class);

    /**
     * Updates dependent systems with new configuration.
     * @param config the new configuration
     */
    public void updateDependencies(UserConfigurations config) {
        logger.info("Updating dependent systems with new configuration...");
        TicketCounter.getInstance().setMaxLimit(config.getTotalTickets());
        TicketCounter.getInstance().resetTicketCount();
        TicketPool.getInstance().resetTicketPool();
        logger.info("Dependent systems updated successfully.");
    }
}
