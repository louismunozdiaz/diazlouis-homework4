import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validator, Validators } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Employee } from '../model/employee';

/**
 * 
 */

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  readonly URL_PREFIX: string = "http://localhost:8080";

  constructor(private httpClient: HttpClient) { }

  /**
   * The following methods communicates with 
   * spring boot endpoints to manipulate the database.
   */
  
  getEmployees(): Observable<Employee[]> {
    return this.httpClient.get<Employee[]>(this.URL_PREFIX + "/employees");
  }

  getEmployee(eid: number): Observable<Employee> {
    return this.httpClient.get<Employee>(this.URL_PREFIX + "/employee/byeid/" + eid);
  }

  addEmployee(employee: Employee): Observable<Employee> {
    return this.httpClient.post<Employee>(this.URL_PREFIX + "/employees", employee);
  }

  putEmployee(employee: Employee): Observable<Employee> {
    return this.httpClient.put<Employee>(this.URL_PREFIX + "/employees", employee);
  }

  deleteEmployee(employee: Employee): Observable<{}> {
    return this.httpClient.delete(this.URL_PREFIX + "/employee/delete/byeid/" + employee.eid);
  }

  // FormGroup used by the employee.component
  form: FormGroup = new FormGroup({
    eid: new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(5)]),
    name: new FormControl('', Validators.required),
    position: new FormControl('', Validators.required),
    yos: new FormControl('', [Validators.required, Validators.min(0)]),
    salary: new FormControl('', [Validators.required, Validators.min(0)]),
    sid: new FormControl('', Validators.required)
  });

  // set the form's default values
  initializeFormGroup() {
    this.form.setValue({
      eid: '',
      name: '',
      position: '',
      yos: '',
      salary: '',
      sid: '',
    })
  }

  // fill the form using an employee object
  populateForm(employee: Employee) {
    this.form.setValue(employee);
  }
}
