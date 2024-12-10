# Real-Time Ticketing System Backend

## Overview
This project is an event ticketing system built using Java, Spring Boot, and Maven and MySQL. It allows users to manage event tickets, including ticket release rates, customer retrieval rates, and maximum ticket capacity.

## Features
1. System Initialization and Configuration
    - CLI-based configuration interface to set up initial parameters:
        - Total Number of Tickets (`totalTickets`)
        - Ticket Release Rate (`ticketReleaseRate`)
        - Customer Retrieval Rate (`customerRetrievalRate`)
        - Maximum Ticket Capacity (`maxTicketCapacity`)
    - Input validation ensures all parameters are within acceptable ranges.
    - Configuration settings are serialized to a JSON file for easy storage and retrieval.

2. Database Integration
    - Connects to a relational database to persist ticket, transaction and user data.
    - Uses Spring Data JPA for easy interaction with the database.
    - Requires user-provided database credentials for setup.

3. Ticket Vendor (Producer)
    - Simulates multiple vendors releasing tickets concurrently.
    - Vendors operate as independent threads using the `Runnable` interface.
    - Thread-safe ticket addition using synchronized methods in `TicketPool`.

4. Customer (Consumer)
    - Simulates multiple customers purchasing tickets concurrently.
    - Customers operate as independent threads using the `Runnable` interface.
    - Thread-safe ticket removal using synchronized methods in `TicketPool`.

5. Logging and Error Handling
    - Logs system activities to both the console and a file.
    - Uses Java's `org.slf4j.Logger` interface for structured logging.
    - Handles exceptions gracefully with meaningful error messages.

6. Simulation
    - Simulates the ticketing system in real-time.
    - Displays the current state of the system at regular intervals.
    - Allows users to pause, resume, or stop the simulation at any time.

## Database Configuration
Before starting the application, you need to configure the database connection. Update the database settings in the `application.yml` file located in the `src/main/resources` directory.

### Example `application.yml`:
  application:
      name: <Table Name>
  datasource:
    url: jdbc:mysql://localhost:3306/<Table Name>?createDatabaseIfNotExist=true&useTimezone=true&serverTimezone=UTC
    username: <your_database_username>
    password: <your_database_password> 
    
----------------------------------
## installation

## Usage
1. Access the application at `http://localhost:8080/api.eventTicket/v1.
2. Follow the on-screen instructions to configure the system.
3. Start the simulation to begin ticketing operations.
4. Monitor the system state and logs to track ticket sales and availability.
5. Pause, resume, or stop the simulation as needed.
6. View the database to see the persisted ticket, transaction, and user data.
7. API Endpoints:
    - `/userConfig/get`: Get user configurations
    - `/userConfig/set`: Set user configurations
    - `/api.eventTicket/logs`: Get system logs
    - `/api.eventTicket/v1/ticket/status`: Get all available tickets
    - `/api.eventTicket/v1/simulation/start`: Start the ticketing simulation
    - `/api.eventTicket/v1/simulation/stop`: Stop the ticketing simulation



