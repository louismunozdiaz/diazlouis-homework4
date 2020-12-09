import { Component, OnInit } from '@angular/core';
import {EmployeeService} from "../../services/employee.service";
import {Employee} from "../../model/employee";
import {SidService} from "../../services/sid.service";

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {

  // to hold the employee to add
  employee: Employee = new Employee();
  // to hold a list of SIDs
  sidList: string[] = [];

  constructor(private employeeService: EmployeeService,
              private sidService: SidService) { }

  ngOnInit(): void {
    this.fetchListOfSID();
  }

  fetchListOfSID() {

    // get a list of SIDs
    this.sidService.getListOfSID().subscribe(
      data => {

        // save the data to the array
        this.sidList = data;

      }
    );

  }

  onSubmit() {

    this.employeeService.save(this.employee).subscribe(
      data => {

        // log the data
        console.log(`added: ${data.name}, ${data.eid}`);

      }
    );

  }
}
