package com.oop.eventticketingsystem.cli.promptprocess.simulation;

import com.oop.eventticketingsystem.cli.promptprocess.PromptProcess;
import com.oop.eventticketingsystem.simulation.ticketSimulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A prompt process to start the simulation
 */
@Component
public class StartSimulationProcess implements PromptProcess {
    private final ticketSimulator ticketSimulator;

    @Autowired
    public StartSimulationProcess(ticketSimulator ticketSimulator) {
        this.ticketSimulator = ticketSimulator;
    }

    @Override
    public void execute() {
        ticketSimulator.simulate();
    }
}
