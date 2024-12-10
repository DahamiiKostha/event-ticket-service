package com.oop.eventticketingsystem.cli;

import com.oop.eventticketingsystem.util.prompt.PromptLogger;
import com.oop.eventticketingsystem.util.prompt.PromptScanner;

/**
 * Manages the prompt handler thread.
 */
public class PromptManager {

    private Thread shellThread;
    private final PromptHandler promptHandler;

    public PromptManager(PromptHandler promptHandler) {
        this.promptHandler = promptHandler;
    }

    /**
     * Starts the PromptHandler in a separate thread.
     */
    public void start() {
        shellThread = new Thread(() -> {
            try {
                promptHandler.run();
            } catch (Exception e) {
                PromptLogger.getInstance().error("Error running shell: " + e.getMessage());
                Thread.currentThread().interrupt(); // Restore the interrupt status
            }
        });
        shellThread.setName("ShellThread");
        shellThread.setDaemon(true); // Set as a daemon thread so it exits when the JVM shuts down
        shellThread.start();
    }

    /**
     * Stops the PromptHandler by interrupting the thread.
     */
    public void stop() {
        if (shellThread != null && shellThread.isAlive()) {
            shellThread.interrupt();
            try {
                shellThread.join(1000); // Wait for the thread to terminate
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupt status
                PromptLogger.getInstance().error("Shell thread interrupted during shutdown.");
            }
        }
        PromptScanner.getInstance().close();
    }
}
