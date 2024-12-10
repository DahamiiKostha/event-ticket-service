package com.oop.eventticketingsystem.controller.logs;

import com.oop.eventticketingsystem.service.logs.LogReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

/**
 * A controller for handling logs
 */
@RestController
public class LogController {

    private final LogReaderService logReaderService;

    @Autowired
    public LogController(LogReaderService logReaderService) {
        this.logReaderService = logReaderService;
    }

    /**
     * Get the logs
     * @return the logs
     */
    @GetMapping("/api.eventTicket/logs")
    public ResponseEntity<Object> getLogs() {
        try {
            return ResponseEntity.ok(
                    logReaderService.readNewLogs());
        } catch (IOException e) {
            return ResponseEntity.status(500).body(
                    "Error reading logs"
            );
        }
    }
}

