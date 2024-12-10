package com.oop.eventticketingsystem.controller.simulation;

import com.oop.eventticketingsystem.dto.response.ResponseMessageDto;
import com.oop.eventticketingsystem.simulation.ticketSimulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A controller for handling the simulation
 */
@RestController
@RequestMapping("/api.eventTicket/v1/simulation")
public class SimulationController {
    ticketSimulator ticketSimulator;

    @Autowired
    public SimulationController(ticketSimulator ticketSimulator) {
        this.ticketSimulator = ticketSimulator;
    }

    /**
     * Start the simulation
     * @return a response entity
     */
    @GetMapping("/start")
    public ResponseEntity<Object> startSimulation() {
        ticketSimulator.simulate();
        return ResponseEntity.status(200).body(new ResponseMessageDto("200 OK", "Simulation started"));
    }

    /**
     * Stop the simulation
     * @return a response entity
     */
    @GetMapping("/stop")
    public ResponseEntity<Object> stopSimulation() {
        ticketSimulator.stopSimulation();
        return ResponseEntity.ok(new ResponseMessageDto("200 OK", "Simulation stopped"));
    }

    /**
     * Get the status of the simulation
     * @return a response entity
     */
    @GetMapping("/status")
    public ResponseEntity<Object> isRunning() {
        return ResponseEntity.ok(ticketSimulator.isRunning());
    }

}

