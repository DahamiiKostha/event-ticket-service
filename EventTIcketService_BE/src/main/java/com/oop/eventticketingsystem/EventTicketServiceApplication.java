package com.oop.eventticketingsystem;

import com.oop.eventticketingsystem.cli.PromptHandler;
import com.oop.eventticketingsystem.cli.PromptManager;
import com.oop.eventticketingsystem.util.prompt.PromptLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Event Ticket Service application.
 */
@SpringBootApplication
public class EventTicketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventTicketServiceApplication.class, args);

        // Initialize and start the PromptManager
        PromptManager promptManager = new PromptManager(new PromptHandler());
        promptManager.start();

        // Add shutdown hooks for resource cleanup
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            promptManager.stop();
            PromptLogger.getInstance().close();
        }));
	}

}
