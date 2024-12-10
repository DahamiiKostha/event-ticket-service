package com.oop.eventticketingsystem.dto.response;

/**
 * A class for ticket status response
 */
public class TicketStatusResponseDto {
    private int currentTicketCount;
    private int maxLimit;

    public TicketStatusResponseDto(int currentTicketCount, int maxLimit) {
        this.currentTicketCount = currentTicketCount;
        this.maxLimit = maxLimit;
    }

    public int getCurrentTicketCount() {
        return currentTicketCount;
    }

    public void setCurrentTicketCount(int currentTicketCount) {
        this.currentTicketCount = currentTicketCount;
    }

    public int getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(int maxLimit) {
        this.maxLimit = maxLimit;
    }
}