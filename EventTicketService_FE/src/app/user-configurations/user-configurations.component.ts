
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserConfigurationsService } from '../services/user-configurations.service';

/**
 * UserConfigurationsComponent is responsible for handling user configurations.
 * It provides a form for users to input configuration data and submit it to the backend service.
 */
@Component({
  selector: 'app-user-configurations',
  standalone: true,
  imports: [CommonModule, FormsModule], // Ensure FormsModule is imported for template-driven forms
  templateUrl: './user-configurations.component.html',
  styleUrls: ['./user-configurations.component.css'],
})
export class UserConfigurationsComponent {
  // Object to store user configuration data
  userConfigurations: any = {
    totalTickets: '',
    ticketReleaseRate: '',
    customerRetrievalRate: '',
    maxTicketCapacity: '',
  };

    /**
   * Constructor to inject the UserConfigurationsService.
   * @param userConfigurationsService - Service to handle user configuration operations.
   */
  constructor(private userConfigurationsService: UserConfigurationsService) {}

    /**
   * Method to handle form submission for user configurations.
   * Converts form input values to numbers and submits the data to the backend service.
   */
  handleSubmit(): void {
    
    const formData = {

      totalTickets: Number(this.userConfigurations.totalTickets),
      ticketReleaseRate: Number(this.userConfigurations.ticketReleaseRate),
      customerRetrievalRate: Number(this.userConfigurations.customerRetrievalRate),
      maxTicketCapacity: Number(this.userConfigurations.maxTicketCapacity),

    }

    // check server status before submitting form data
    console.log('Submitting form data:', formData);

    //Submit the form data to the backend service
    this.userConfigurationsService.saveConfigurations(formData).subscribe(
      (response) => {
        console.log('Configuration saved successfully:', response);
        alert('Configuration saved successfully!');
      },
      
      (error) => {
        // Handle errors
        console.error('Error saving configuration:', error);
        if (error.status === 0) {
          alert('Cannot connect to the backend. Please ensure it is running.');
        } else {
          alert(`saving configuration: ${error.statusText}\n`+
          JSON.stringify(formData, null, 2)
          );

        }
      },
    );

  }
}
