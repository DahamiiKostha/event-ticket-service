package com.oop.eventticketingsystem.service.configurations;

import com.oop.eventticketingsystem.core.Configurations.ConfigurationManager;
import com.oop.eventticketingsystem.model.UserConfigurations;
import com.oop.eventticketingsystem.exceptions.ConfigNotFoundException;
import org.springframework.stereotype.Service;

/**
 * A class for UserConfigurationService
 */
@Service
public class UserConfigurationService {
    private final ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    // Get the user configurations
    public UserConfigurations getConfig() throws ConfigNotFoundException {
        return configurationManager.getConfigurations();
    }

    // Set the user configurations
    public void setConfig(UserConfigurations userConfigurations) {
        configurationManager.setUserConfigurations(userConfigurations);
    }
}
