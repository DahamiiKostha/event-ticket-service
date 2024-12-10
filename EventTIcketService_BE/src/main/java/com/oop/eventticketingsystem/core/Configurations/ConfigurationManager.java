package com.oop.eventticketingsystem.core.Configurations;

import com.oop.eventticketingsystem.exceptions.ConfigNotFoundException;
import com.oop.eventticketingsystem.model.UserConfigurations;
import com.oop.eventticketingsystem.util.fileIoHandler.ConfigurationIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * A class for managing user configurations.
 */
public class ConfigurationManager {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationManager.class);

    private final ConfigurationIO configurationIO; // Dependency for I/O
    private UserConfigurations config; // User configuration object
    private final ReadWriteLock lock = new ReentrantReadWriteLock(); // Thread-safe read/write lock

    /**
     * Constructor for ConfigurationManager
     * @param configurationIO the ConfigurationIO object
     */
    private ConfigurationManager(ConfigurationIO configurationIO) {
        this.configurationIO = configurationIO;
    }

    // Singleton pattern
    private static class SingletonHelper {
        private static final ConfigurationManager INSTANCE = new ConfigurationManager(ConfigurationIO.getInstance());
    }

    public static ConfigurationManager getInstance() {
        return SingletonHelper.INSTANCE;
    }

    /**
     * Retrieves the current configuration, loading it if necessary.
     *
     * @return UserConfigurations The current user configuration.
     * @throws ConfigNotFoundException If configuration cannot be found or loaded.
     */
    public UserConfigurations getConfigurations() throws ConfigNotFoundException {
        lock.readLock().lock(); // Allow concurrent reads
        try {
            if (config != null) {
                return config;
            }
        } finally {
            lock.readLock().unlock();
        }

        // Upgrade to write lock if configurations is not yet initialized
        lock.writeLock().lock();
        try {
            if (config == null) { // Double-check to prevent redundant loading
                logger.info("Loading user configurations from file...");
                config = configurationIO.loadConfig();
                if (config == null) {
                    throw new ConfigNotFoundException("User Configs are not defined. Please set the user configs.");
                }
                logger.info("User configurations successfully loaded: {}", config);
            }
        } finally {
            lock.writeLock().unlock();
        }

        return config;
    }

    /**
     * Updates the configuration and saves it persistently.
     *
     * @param userConfigurations The new user configuration.
     */
    public void setUserConfigurations(UserConfigurations userConfigurations) {
        lock.writeLock().lock(); // Prevent concurrent writes
        try {
            logger.info("Updating user configurations...");
            this.config = userConfigurations;
            configurationIO.saveConfig(userConfigurations);

            // Notify dependent systems
            new ConfigurationUpdater().updateDependencies(userConfigurations);

            logger.info("User configurations successfully saved and dependencies updated: {}", userConfigurations);
        } finally {
            lock.writeLock().unlock();
        }
    }

}
