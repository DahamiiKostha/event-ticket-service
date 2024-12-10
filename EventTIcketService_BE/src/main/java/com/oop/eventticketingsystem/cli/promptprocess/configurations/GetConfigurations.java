package com.oop.eventticketingsystem.cli.promptprocess.configurations;

import com.oop.eventticketingsystem.cli.promptprocess.PromptProcess;
import com.oop.eventticketingsystem.core.Configurations.ConfigurationManager;
import com.oop.eventticketingsystem.model.UserConfigurations;
import com.oop.eventticketingsystem.exceptions.ConfigNotFoundException;
import com.oop.eventticketingsystem.util.prompt.PromptLogger;


/**
 * A prompt process to get the current configurations
 */

// GetConfigurations class implements PromptProcess
public class GetConfigurations implements PromptProcess {

    private final ConfigurationManager configurationManager = ConfigurationManager.getInstance();
    private final PromptLogger promptLogger = PromptLogger.getInstance();

    // Execute the prompt process
    public void execute() {
        UserConfigurations conf;
        try {
            conf = configurationManager.getConfigurations();
        } catch (ConfigNotFoundException e) {
            promptLogger.error("No configuration found. Please set configuration first.");
            return;
        }

        // Print the configurations
        promptLogger.verbose(conf.toString());
    }
}
