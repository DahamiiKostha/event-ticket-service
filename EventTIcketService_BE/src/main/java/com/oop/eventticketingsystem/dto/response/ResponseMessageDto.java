package com.oop.eventticketingsystem.dto.response;

/**
 * A class for response messages
 */
public class ResponseMessageDto {
    private final String status;
    private final String message;

    public ResponseMessageDto(String status, String message) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
