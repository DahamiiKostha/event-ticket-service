import { Component } from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';

/**
 * It sets up the main structure and routing for the application.
 */
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Event Ticket Booking Application';
}
