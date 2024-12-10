package com.oop.eventticketingsystem.util.prompt;

import java.io.PrintWriter;

/**
 * A thread-safe shell logger with configurable log levels and color-coded output.
 */
public class PromptLogger implements PromptCloser {

    private final PrintWriter writer = new PrintWriter(System.out);

    // Private constructor for Singleton
    private PromptLogger() {
    }

    private static final class LoggerHolder {
        private static final PromptLogger INSTANCE = new PromptLogger();
    }

    /**
     * Returns the singleton instance of PromptLogger.
     *
     * @return The singleton instance.
     */
    public static PromptLogger getInstance() {
        return LoggerHolder.INSTANCE;
    }

    public void log(String message, LogLevel level) {
        synchronized (this) {
            writer.println(level.format(message));
            writer.flush();
        }
    }

    // Convenience methods for each log level
    public void info(String message) {
        log(message, LogLevel.INFO);
    }

    public void usageInfo(String message) {
        log(message, LogLevel.USAGE);
    }

    public void warn(String message) {
        log(message, LogLevel.WARN);
    }

    public void error(String message) {
        log(message, LogLevel.ERROR);
    }

    public void debug(String message) {
        log(message, LogLevel.DEBUG);
    }

    public void trace(String message) {
        log(message, LogLevel.TRACE);
    }

    public void success(String message) {
        log(message, LogLevel.SUCCESS);
    }

    public void failure(String message) {
        log(message, LogLevel.FAILURE);
    }

    public void fatal(String message) {
        log(message, LogLevel.FATAL);
    }

    public void notice(String message) {
        log(message, LogLevel.NOTICE);
    }

    public void critical(String message) {
        log(message, LogLevel.CRITICAL);
    }

    public void alert(String message) {
        log(message, LogLevel.ALERT);
    }

    public void verbose(String message) {
        log(message, LogLevel.VERBOSE);
    }

    /**
     * Log levels with color-coding for console output.
     */
    private enum LogLevel {
        INFO("\u001B[92m"),       // Light Green
        USAGE("\u001B[33m"),            // Soft Yellow
        WARN("\u001B[33m"),       // Orange-Yellow
        ERROR("\u001B[91m"),      // Bright Red
        DEBUG("\u001B[36m"),      // Cyan
        TRACE("\u001B[95m"),      // Light Purple
        SUCCESS("\u001B[32m"),    // Standard Green
        FAILURE("\u001B[31m"),    // Standard Red
        FATAL("\u001B[97;41m"),   // Bright White with Red Background
        NOTICE("\u001B[94m"),     // Blue
        CRITICAL("\u001B[93m"),   // Bright Yellow
        ALERT("\u001B[38;5;178m"),// Deep Yellow
        VERBOSE("\u001B[37m");    // Light Gray

        private final String colorCode;

        LogLevel(String colorCode) {
            this.colorCode = colorCode;
        }

        public String format(String message) {
            return String.format("%s%s\u001B[0m", colorCode, message);
        }
    }

    @Override
    public void close() {
        writer.close();
    }
}
