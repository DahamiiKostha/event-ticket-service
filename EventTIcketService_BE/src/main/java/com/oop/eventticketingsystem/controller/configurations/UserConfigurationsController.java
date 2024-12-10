package com.oop.eventticketingsystem.controller.configurations;

import com.oop.eventticketingsystem.dto.response.ResponseMessageDto;
import com.oop.eventticketingsystem.model.UserConfigurations;
import com.oop.eventticketingsystem.service.configurations.UserConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * A controller for handling user configurations
 */
@RestController
@RequestMapping("/api.eventTicket/v1/userConfig")

public class UserConfigurationsController {
    private static final Logger log = LoggerFactory.getLogger(UserConfigurationsController.class);
    private final UserConfigurationService userConfigurationService;

    @Autowired
    public UserConfigurationsController(UserConfigurationService userConfigurationService) {
        this.userConfigurationService = userConfigurationService;
    }

    /**
     * Get the user configurations
     * @return the user configurations
     */
    @GetMapping("/get")
    public ResponseEntity<Object> getConfig() {
        try {
            return ResponseEntity.ok(userConfigurationService.getConfig());
        } catch (Exception e) {
            log.error("Error getting user configurations", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseMessageDto("400 Bad Request", "Config not found"));
        }
    }

    /**
     * Set the user configurations
     * @param userConfigurations the user configurations
     * @return a response entity
     */
    @PostMapping("/set")
    public ResponseEntity<Object> setConfig(@RequestBody UserConfigurations userConfigurations) {
        try {
            userConfigurationService.setConfig(userConfigurations);
            log.info("User configurations updated successfully");
            return ResponseEntity.ok("User configurations updated successfully");
        } catch (Exception e) {
            log.error("Error setting user configurations", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseMessageDto("400 Bad Request", "Could not update user configurations"));
        }

    }



}
