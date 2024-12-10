package com.oop.eventticketingsystem.exceptions;

/**
 * A class for ConfigNotFoundException
 */
public class ConfigNotFoundException extends RuntimeException {
    public ConfigNotFoundException(String message) {
        super(message);
    }
}
