package com.oop.eventticketingsystem.util.helper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputValidator {
    private static final Logger log = LoggerFactory.getLogger(InputValidator.class);

    /**
     * Validates that the input string represents a positive integer.
     *
     * @param input The input string to validate.
     * @return The parsed positive integer.
     * @throws IllegalArgumentException If the input is invalid.
     */
    public static int validatePositiveInteger(String input) {
        try {
            // Parse the input as an integer
            int value = Integer.parseInt(input);

            // Validate that the integer is positive
            if (value < 0) {
                log.error("Invalid input: {}. Integer must be positive.", input);
                throw new IllegalArgumentException("Integer must be positive");
            }

            return value;

        } catch (NumberFormatException e) {
            // Handle invalid integer input
            log.error("Invalid input: {}. Must be an integer.", input);
            throw new IllegalArgumentException("Must be an integer");
        }
    }
}
