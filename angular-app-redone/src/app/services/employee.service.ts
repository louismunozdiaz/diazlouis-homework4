import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Employee} from '../model/employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  readonly baseUrl: string = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  getOne(eid: number): Observable<Employee> {
    return this.http.get<Employee>(`${this.baseUrl}/employee/byeid/${eid}`);
  }

  update(employee: Employee): Observable<Employee> {
    return this.http.put<Employee>(`${this.baseUrl}/employees`, employee);
  }

  save(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(`${this.baseUrl}/employees`, employee);
  }

  delete(eid: number): Observable<Employee> {
    return this.http.delete<Employee>(`${this.baseUrl}/employee/delete/byeid/${eid}`);
  }

  getAll(sid?: string, pos?: string, dept?: string): Observable<Employee[]> {
    if (sid) {
      if (pos) {
        return this.http.get<Employee[]>(`${this.baseUrl}/${sid}/employees/${pos}`);
      } else {
        return this.http.get<Employee[]>(`${this.baseUrl}/${sid}/employees`);
      }
    } else if (pos) {
      return this.http.get<Employee[]>(`${this.baseUrl}/employees/${pos}`);
    } else if (dept) {
      return this.http.get<Employee[]>(`${this.baseUrl}/employees/bydepartment/${dept}`);
    } else {
      return this.http.get<Employee[]>(`${this.baseUrl}/employees`);
    }
  }

  getAllOrderByEidAsc(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.baseUrl}/employees/sortedbyeidasc`);
  }

  getAllOrderByEidDesc(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.baseUrl}/employees/sortedbyeiddesc`);
  }

  getAllOrderByNameAsc(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.baseUrl}/employees/sortedbynameasc`);
  }

  getAllOrderByNameDesc(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.baseUrl}/employees/sortedbynamedesc`);
  }

  getAllOrderByPosAsc(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.baseUrl}/employees/sortedbyposasc`);
  }

  getAllOrderByPosDesc(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.baseUrl}/employees/sortedbyposdesc`);
  }

}
