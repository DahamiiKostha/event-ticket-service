import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SimulationService {
  private baseUrl = 'http://localhost:8080/api.eventTicket/v1/simulation';

  constructor(private http: HttpClient) {}

  startSimulation(): Observable<any> {
    return this.http.get(`${this.baseUrl}/start`);
  }

  stopSimulation(): Observable<any> {
    return this.http.get(`${this.baseUrl}/stop`);
  }

  isSimulationRunning(): Observable<any> {
    return this.http.get(`${this.baseUrl}/status`);
  }

}
