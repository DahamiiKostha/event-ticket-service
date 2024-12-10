package com.oop.eventticketingsystem.cli;

import com.oop.eventticketingsystem.cli.promptprocess.PromptProcess;
import com.oop.eventticketingsystem.cli.promptprocess.configurations.GetConfigurations;
import com.oop.eventticketingsystem.cli.promptprocess.configurations.SetConfigurations;
import com.oop.eventticketingsystem.cli.promptprocess.logs.PromptLogs;
import com.oop.eventticketingsystem.util.prompt.PromptScanner;
import com.oop.eventticketingsystem.util.prompt.PromptLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * A handler for the interactive CLI
 */
@Component
public class PromptHandler implements CommandLineRunner {
    private final PromptLogger promptLogger = PromptLogger.getInstance();
    private final PromptScanner scanner = PromptScanner.getInstance();
    private final PromptProcess serverLogs;
    private final PromptProcess getConfigurations;
    private final PromptProcess setConfigurations;

    @Autowired
    private PromptProcess startSimulationProcess;
    @Autowired
    private PromptProcess stopSimulationProcess;

    /**
     * Constructor for the PromptHandler
     */
    public PromptHandler() {
        this.serverLogs = new PromptLogs();
        this.getConfigurations = new GetConfigurations();
        this.setConfigurations = new SetConfigurations();
    }

    /**
     * Handle the user selection
     * @param selection the user selection
     */
    private void handleSelection(int selection){
        switch (selection){
            case 1:
                serverLogs.execute();
                break;
            case 2:
                getConfigurations.execute();
                break;
            case 3:
                setConfigurations.execute();
                break;
            case 4:
                startSimulationProcess.execute();
                break;
            case 5:
                stopSimulationProcess.execute();
                break;
            case 6:
                System.exit(0);
                break;
            default:
                promptLogger.error("Invalid selection. Please try again.");
        }
    }

    /**
     * Run the CLI
     * @param args the command line arguments
     */
    @Override
    public void run(String... args) {
        promptLogger.usageInfo("Interactive CLI started. Type 'exit' to quit.");

        while (true) {
            promptLogger.usageInfo("Select an option:");
            promptLogger.usageInfo("1. View server logs");
            promptLogger.usageInfo("2. Get user configuration");
            promptLogger.usageInfo("3. Set user configuration");
            promptLogger.usageInfo("4. Run simulation");
            promptLogger.usageInfo("5. Stop simulation");
            promptLogger.usageInfo("6. Exit");
            promptLogger.usageInfo("Enter selection: ");

            // Read user input
            String input = "";
            try {
                input = scanner.scanString();
            } catch (IllegalArgumentException e) {
                promptLogger.error(e.getMessage());
            }

            if (handleExitCommand(input)) {
                break;
            }
            processInput(input);
        }
        promptLogger.usageInfo("CLI session ended.");
    }

    /**
     * Handle the exit command
     * @param input the user input
     * @return true if the user wants to exit, false otherwise
     */
    private boolean handleExitCommand(String input) {
        if ("exit".equalsIgnoreCase(input)) {
            promptLogger.usageInfo("Exiting CLI...");
            return true; // Signal that we should exit
        }
        return false; // Continue running
    }

    /**
     * Process the user input
     * @param input the user input
     */
    private void processInput(String input) {
        try {
            int selection = Integer.parseInt(input);
            handleSelection(selection); // Process the valid selection
        } catch (NumberFormatException e) {
            promptLogger.error("Invalid selection. Please enter a valid number.");
        }
    }
}
