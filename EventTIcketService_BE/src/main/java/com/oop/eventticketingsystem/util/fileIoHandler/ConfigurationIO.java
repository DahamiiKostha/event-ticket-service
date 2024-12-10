package com.oop.eventticketingsystem.util.fileIoHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oop.eventticketingsystem.core.Configurations.ConfigSerializer;
import com.oop.eventticketingsystem.model.UserConfigurations;
import com.oop.eventticketingsystem.util.Global;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * ConfigurationIO is responsible for reading and writing configurations to a file.
 */
public class ConfigurationIO {
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationIO.class);
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private ConfigurationIO() {
    }

    private static class ConfigIOHolder {
        private static final ConfigurationIO INSTANCE = new ConfigurationIO();
    }

    public static ConfigurationIO getInstance() {
        return ConfigIOHolder.INSTANCE;
    }

    // Save configuration to a file
    public void saveConfig(UserConfigurations config) {
        // serialize the object to JSON
        ConfigSerializer configSerializer = new ConfigSerializer(gson);
        String json = configSerializer.serialize(config);

        // write the JSON string to the file
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(Global.CONFIG_PATH))) {
            bufferedWriter.write(json);
            logger.info("Config saved successfully");
        } catch (IOException e) {
            logger.error("Error saving configurations", e);
        }
    }

    // Load configuration from a file
    public UserConfigurations loadConfig() {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(Global.CONFIG_PATH))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
            }
            logger.info("Config loaded successfully");
        } catch (IOException e) {
            logger.error("Error loading configurations", e);
        }

        // deserialize the JSON string to an object
        ConfigSerializer configSerializer = new ConfigSerializer(gson);
        return configSerializer.deserialize(stringBuilder.toString());
    }
}
