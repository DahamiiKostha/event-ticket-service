import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserConfigurationsService {
  private apiUrl = 'http://localhost:8080/api.eventTicket/v1/userConfig';

  constructor(private http: HttpClient) {}

  saveConfigurations(formData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/set`, formData);
  }
}
