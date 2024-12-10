# EventTicketService_FE
This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 18.0.0

## Overview
EventTicketService_FE is the frontend component of the Event Ticket Service application. 
The UI displays Ticket status and provides interaction options, including configuration settings, system controls (simulation), and log viewing

## Features
- Display Ticket Availability: Show real-time availability of tickets.
- Configuration Settings: Input fields for configuring system parameters.
- Control Panel: Buttons to start and stop the simulation process.
- Log Display: View logs for monitoring system events.

## Development Server
Run `ng serve` for a development server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Technologies Used
- Angular: Framework for building the UI.
- TypeScript: Language for writing maintainable and type-safe code.
- RxJS: Library for reactive programming and state management.
- HTML & CSS: Markup and styling.
- Node.js: Runtime environment for Angular CLI.
- REST APIs: Backend interaction via polling.


## How It Works

## UI Components
The application is structured into the following Angular components:

1. ConfigurationForm:  
   For entering configuration settings.
        ◦ Total Number of Tickets (totalTickets)
        ◦ Ticket Release Rate (ticketReleaseRate)
        ◦ Customer Retrieval Rate (customerRetrievalRate)
        ◦ Maximum Ticket Capacity (maxTicketCapacity)

2. TicketDisplay:  
   To display the current availability of tickets.

3. ControlPanel:  
   Provides start and stop buttons for simulation process.

4. LogDisplay:  
   Shows system logs for real-time monitoring.


## State Management

Polling Mechanism: The Components uses RxJS to periodically poll the backend and fetch updates.
Service-Based Communication: The Components directly interacts with a Services to fetch logs and update the view.
Subscription Management: Ensures clean-up of subscriptions to avoid memory leaks.

### Backend Interaction
- **Polling Mechanism**:  
  The UI fetches updates periodically from the backend to ensure the display is synchronized with real-time data.  
  Example implementation:
  ```typescript
  import { interval } from 'rxjs';
  import { switchMap } from 'rxjs/operators';

    ngOnInit(): void {
    // Poll the backend every 2 seconds for new logs
    this.subscription = interval(500).subscribe(() => {
      this.logService.fetchLogs().subscribe((data) => {
        this.logs += data; // Append new logs
      });
    });
  }


-----------------------------------------------------
## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/EventTicketService_FE.git
    ```

2. Navigate to the project directory:
    ```bash
    cd EventTicketService_FE
    ```

3. Install dependencies:
    ```bash
    npm install
    ```

## Running the Application

1. Start the development server:
    ```bash
    npm start
    ```

2. Open your browser and navigate to `http://localhost:3000`.

## Building for Production

1. Build the application:
    ```bash
    npm run build
    ```
