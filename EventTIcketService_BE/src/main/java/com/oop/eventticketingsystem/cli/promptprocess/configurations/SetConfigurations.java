package com.oop.eventticketingsystem.cli.promptprocess.configurations;

import com.oop.eventticketingsystem.cli.promptprocess.PromptProcess;
import com.oop.eventticketingsystem.core.Configurations.ConfigurationManager;
import com.oop.eventticketingsystem.model.UserConfigurations;
import com.oop.eventticketingsystem.util.prompt.PromptLogger;
import com.oop.eventticketingsystem.util.prompt.PromptScanner;

/**
 * A prompt process to set the configurations
 */
public class SetConfigurations implements PromptProcess {
    private final ConfigurationManager configurationManager = ConfigurationManager.getInstance();
    private final PromptLogger promptLogger = PromptLogger.getInstance();
    private final PromptScanner scan = PromptScanner.getInstance();

    // Execute the prompt process
    public void execute() {
        setConfig();
    }

    // Set the configurations
    private void setConfig(){
        int totalTickets = getConfigValue("total tickets");
        int ticketReleaseRate = getConfigValue("ticket release rate");
        int customerRetrievalRate = getConfigValue("customer retrieval rate");
        int maxTicketCapacity = getConfigValue("max ticket capacity");

        // set the user configuration
        UserConfigurations userConfigurations = new UserConfigurations(
                totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
        configurationManager.setUserConfigurations(userConfigurations);
    }

    // Get the configuration value from the user
    private int getConfigValue(String configName){
        while (true){
            promptLogger.usageInfo("Enter " + configName + ": ");
            try {
                return scan.scanPositiveInt();
            } catch (IllegalArgumentException e) {
                promptLogger.error(e.getMessage());
            }
        }
    }
}