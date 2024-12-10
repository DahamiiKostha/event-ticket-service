package com.oop.eventticketingsystem.core.Configurations;

import com.google.gson.Gson;
import com.oop.eventticketingsystem.model.UserConfigurations;

/**
 * A class for serializing and deserializing UserConfigurations objects
 */
public class ConfigSerializer {
    private final Gson gson;

    /**
     * Constructor for ConfigSerializer
     * @param gson the Gson object
     */
    public ConfigSerializer(Gson gson) {
        this.gson = gson;
    }

    /**
     * Serialize a UserConfigurations object to a JSON string
     * @param object the UserConfigurations object
     * @return the JSON string
     */
    public String serialize(UserConfigurations object) {
        return gson.toJson(object);
    }

    /**
     * Deserialize a JSON string to a UserConfigurations object
     * @param json the JSON string
     * @return the UserConfigurations object
     */
    public UserConfigurations deserialize(String json) {
        return gson.fromJson(json, UserConfigurations.class);
    }
}
