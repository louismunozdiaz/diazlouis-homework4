import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validator, Validators } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Employee } from '../model/employee';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private url: string = "http://localhost:8080/employees";

  constructor(private httpClient: HttpClient) { }

  getEmployees(): Observable<Employee[]> {
    return this.httpClient.get<Employee[]>(this.url);
  }

  addEmployee(employee: Employee): Observable<Employee> {
    return this.httpClient.post<Employee>(this.url, employee);
  }

  putEmployee(employee: Employee): Observable<Employee> {
    return this.httpClient.put<Employee>(this.url, employee);
  }

  deleteEmployee(employee: Employee): Observable<{}> {
    const delete_url = "http://localhost:8080/employee/delete/byeid/";
    return this.httpClient.delete(delete_url + employee.eid);
  }

  populateForm(employee: Employee) {
    this.form.setValue(employee);
  }

  form: FormGroup = new FormGroup({
    eid: new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(5)]),
    name: new FormControl('', Validators.required),
    position: new FormControl('', Validators.required),
    yos: new FormControl('', [Validators.required, Validators.min(0)]),
    salary: new FormControl('', [Validators.required, Validators.min(0)]),
    sid: new FormControl('', Validators.required)
  });

  initializeFormGroup() {
    this.form.setValue({
      eid:'',
      name:'',
      position:'',
      yos:'',
      salary:'',
      sid:'',
    })
  }
}
