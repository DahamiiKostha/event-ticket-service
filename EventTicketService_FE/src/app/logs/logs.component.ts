import { Component, OnInit, OnDestroy } from '@angular/core';
import { interval, Subscription } from 'rxjs';
import {LogService} from "../services/logs.service";


/**
 * LogsComponent is responsible for displaying real-time logs.
 */
@Component({
  selector: 'app-log',
  templateUrl: './logs.component.html',
  standalone: true,
  styleUrls: ['./logs.component.css']
})
export class LogComponent implements OnInit, OnDestroy {
  logs: string = '';
  private subscription: Subscription | null = null;

   /**
   * Constructor to inject the LogService.
   * @param logService - Service to fetch logs.
   */ 
  constructor(private logService: LogService) {}


    /**
   * Lifecycle hook that is called after data-bound properties are initialized.
   * Starts polling the backend every 0.5 seconds for new logs.
   */
  ngOnInit(): void {
    // Poll the backend every 0.5 seconds for new logs
    this.subscription = interval(500).subscribe(() => {
      this.logService.fetchLogs().subscribe((data) => {
        this.logs += data; // Append new logs
      });
    });
  }

  ngOnDestroy(): void {
    // Unsubscribe to avoid memory leaks
    this.subscription?.unsubscribe();
  }
}
