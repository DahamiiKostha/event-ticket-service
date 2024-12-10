import { Component, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SimulationService } from '../services/simulation.service';
import { interval, Subscription } from 'rxjs';

/**
 * SimulationComponent is responsible for managing and displaying the simulation status.
  */
@Component({
  selector: 'app-simulation',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './simulation.component.html',
  styleUrls: ['./simulation.component.css'],
})
export class SimulationComponent implements OnInit, OnDestroy {
  // Flag to indicate if the simulation is running
  isSimulationRunning = false;
  // Subscription to check the simulation status
  private simulationStatusSubscription: Subscription | null = null;

   /**
   * Constructor to inject the SimulationService.
   * @param simulationService - Service to manage simulation operations.
   */
  constructor(private simulationService: SimulationService) {}

    /**
     * Starts polling the simulation status every 0.5 seconds.
   */
  ngOnInit(): void {
    // Check the simulation status every 0.5 seconds
    this.simulationStatusSubscription = interval(500).subscribe(() => {
      this.checkSimulationStatus();
    });
  }

  ngOnDestroy(): void {
    // Unsubscribe to avoid memory leaks
    this.simulationStatusSubscription?.unsubscribe();
  }

  /**
   * Method to start the simulation.
   * Displays an alert if the simulation is already running.
   */
  startSimulation(): void {
    if (this.isSimulationRunning) {
      alert('Simulation is already running!');
      return;
    }

    this.simulationService.startSimulation().subscribe(
      (response) => {
        console.log('Simulation started:', response);
        alert('Simulation started successfully!');
        this.isSimulationRunning = true;
      },
      (error) => {
        console.error('Error starting simulation:', error);
        alert('Error starting simulation');
      }
    );
  }

  /**
   * Method to check the current status of the simulation.
   * Updates the isSimulationRunning flag based on the response.
   */
  stopSimulation(): void {
    if (!this.isSimulationRunning) {
      alert('Simulation is not running!');
      return;
    }

    this.simulationService.stopSimulation().subscribe(
      (response) => {
        console.log('Simulation stopped:', response);
        this.isSimulationRunning = false;
        alert('Simulation stopped successfully!');
      },
      (error) => {
        console.error('Error stopping simulation:', error);
        alert('Error stopping simulation');
      }
    );
  }

  private checkSimulationStatus(): void {
    this.simulationService.isSimulationRunning().subscribe(
      (response:boolean) => {
        console.log('Simulation status:', response);
        this.isSimulationRunning = response;
      },
      (error) => {
        console.error('Error fetching simulation status:', error);
        this.isSimulationRunning = false;
      }
    );
  }
}
