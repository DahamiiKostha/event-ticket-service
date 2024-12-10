import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TicketStatService {
  private baseUrl = 'http://localhost:8080/api.eventTicket/v1/ticket';

  constructor(private http: HttpClient) {}

  getStat(): Observable<any> {
    return this.http.get(`${this.baseUrl}/status`);
  }
}
