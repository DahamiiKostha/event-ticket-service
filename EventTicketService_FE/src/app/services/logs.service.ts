import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LogService {
  private readonly logEndpoint = 'http://localhost:8080/api.eventTicket/logs';

  constructor(private http: HttpClient) {}

  fetchLogs(): Observable<string> {
    return this.http.get(this.logEndpoint, { responseType: 'text' });
  }
}
