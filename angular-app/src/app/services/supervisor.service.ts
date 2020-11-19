import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Supervisor } from '../model/supervisor';

@Injectable({
  providedIn: 'root'
})
export class SupervisorService {

  private url: string = "http://localhost:8080/supervisors";

  constructor(private httpClient: HttpClient) { }

  getSupervisors(): Observable<Supervisor[]> {
    return this.httpClient.get<Supervisor[]>(this.url);
  }
}
