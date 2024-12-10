
package com.oop.eventticketingsystem.util.prompt;

import com.oop.eventticketingsystem.util.helper.InputValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * PromptScanner is responsible for scanning user input.
 */
public class PromptScanner implements PromptCloser {
    private static final Logger log = LoggerFactory.getLogger(PromptScanner.class);
    private static final Scanner scanner = new Scanner(System.in);

    private PromptScanner() {
        // Prevent instantiation
    }

    private static class ScanHolder {
        private static final PromptScanner INSTANCE = new PromptScanner();
    }

    public static PromptScanner getInstance() {
        return ScanHolder.INSTANCE;
    }

    /**
     * Scans a positive integer input from the user.
     *
     * @return The validated positive integer.
     * @throws IllegalArgumentException If the input is invalid.
     */
    public int scanPositiveInt() throws IllegalArgumentException {
        String input = scanner.nextLine().trim();
        try {
            return InputValidator.validatePositiveInteger(input);
        } catch (IllegalArgumentException e) {
            log.error("Invalid input: {}", input, e);
            throw e;
        }
    }

    /**
     * Scans a valid string input from the user.
     *
     * @return The validated string input.
     * @throws IllegalArgumentException If the input is invalid.
     */
    public String scanString() throws IllegalArgumentException {
        if (!scanner.hasNextLine()) {
            log.error("No line found in input");
            throw new IllegalArgumentException("No line found in input");
        }

        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            log.error("Input cannot be empty");
            throw new IllegalArgumentException("Input cannot be empty");
        }
        if (!input.matches("[a-zA-Z0-9 ]+")) {
            log.error("Input contains invalid characters: {}", input);
            throw new IllegalArgumentException("Input contains invalid characters");
        }
        return input;
    }

    /**
     * Returns the underlying scanner instance.
     *
     * @return The scanner.
     */
    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Closes the scanner.
     */
    @Override
    public void close() {
        scanner.close();
    }
}
