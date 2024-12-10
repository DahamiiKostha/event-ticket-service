package com.oop.eventticketingsystem.simulation;

import com.oop.eventticketingsystem.core.Configurations.ConfigurationManager;
import com.oop.eventticketingsystem.core.Configurations.ConfigurationUpdater;
import com.oop.eventticketingsystem.exceptions.ConfigNotFoundException;


import com.oop.eventticketingsystem.service.ticketLimiter.TicketCountLimiter;
import com.oop.eventticketingsystem.simulation.data.DataInit;
import com.oop.eventticketingsystem.simulation.ticketParticipant.ThreadHandler;
import com.oop.eventticketingsystem.util.prompt.PromptLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Simulate the eventTransaction ticket service
 */
@Component
public class ticketSimulator {
    private static final Logger log = LoggerFactory.getLogger(ticketSimulator.class);
    private final DataInit dataInit;
    private final ThreadHandler threadHandler = new ThreadHandler();
    private final ConfigurationManager configurationManager = ConfigurationManager.getInstance();
    private final PromptLogger shellLogger = PromptLogger.getInstance();

    @Autowired
    public ticketSimulator(DataInit dataInit) {
        this.dataInit = dataInit;
    }

    /**
     * Simulate vendor and customer interactions
     */
    public void simulate() {
        int CustomerRetrievalRate;
        int TicketReleaseRate;
        try {
            CustomerRetrievalRate = configurationManager.getConfigurations().getCustomerRetrievalRate();
            TicketReleaseRate = configurationManager.getConfigurations().getTicketReleaseRate();
        } catch (ConfigNotFoundException e) {
            log.error("Error while retrieving configuration: {}", e.getMessage());
            shellLogger.fatal("Could not start simulation | Error while retrieving configuration: " + e.getMessage());
            return;
        }
        dataInit.init();
        startTicketLimiter();
        ConfigurationUpdater configurationUpdater = new ConfigurationUpdater();
        configurationUpdater.updateDependencies(configurationManager.getConfigurations());
        threadHandler.startSimulation(TicketReleaseRate, CustomerRetrievalRate);
    }

    private void startTicketLimiter() {
        // start ticket count ticketLimiter
        Thread ticketCountLimiter = new Thread(new TicketCountLimiter(threadHandler));
        ticketCountLimiter.setName("TicketCountLimiter");
        ticketCountLimiter.start();
    }

    public void stopSimulation() {
        threadHandler.stopAll();
    }


    public boolean isRunning() {
        return threadHandler.isRunning();
    }
}
