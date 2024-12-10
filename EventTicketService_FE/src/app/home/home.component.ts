import {Component, OnDestroy, OnInit} from '@angular/core';
import {TicketStatService} from "../services/ticket-stat.service";
import {interval, Subscription} from "rxjs";


/**
 * HomeComponent is responsible for displaying the home page and managing available ticket statistics.
 */
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit, OnDestroy{
  count: number = 0;
  limit: number = 0;
  pollingSubscription: Subscription | undefined;

    /**
   * Constructor to inject the TicketStatService.
   * @param ticketStatService - Service to fetch ticket statistics.
   */
  constructor(private ticketStatService: TicketStatService) {
  }

   /**
   * Starts polling the ticket statistics every 0.5 seconds.
   */
  ngOnInit() {
    // update ticket status every 0.5 second using rxjs interval
    this.updateStat();
    this.pollingSubscription = interval(500).subscribe(() => this.updateStat());
  }

  /**
   * Fetches the ticket statistics by calling the TicketStatService.
   */
  updateStat() {
    this.ticketStatService.getStat().subscribe(
      (response) => {
        // update ticket status with the response
        this.count = response.currentTicketCount;
        this.limit = response.maxLimit;
      },
      (error) => {
        // log error to console
        console.error('Error getting ticket status:', error);
      }
    );
  }

  /**
   * Unsubscribes from the polling subscription when the component is destroyed.
   */
  ngOnDestroy(): void {
    if (this.pollingSubscription) {
      this.pollingSubscription.unsubscribe();
    }
  }
}
