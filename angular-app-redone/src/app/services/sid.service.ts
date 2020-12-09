import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SidService {

  // base url
  readonly baseUrl: string = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getListOfSID(): Observable<string[]> {
    return this.http.get<string[]>(`${this.baseUrl}/supervisors/sidList`);
  }
}
