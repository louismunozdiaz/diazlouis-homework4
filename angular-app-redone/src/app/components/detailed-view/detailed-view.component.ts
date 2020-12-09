import { Component, OnInit } from '@angular/core';
import {EmployeeService} from "../../services/employee.service";
import {Router, ActivatedRoute} from "@angular/router";
import {Employee} from "../../model/employee";
import {SidService} from "../../services/sid.service";

@Component({
  selector: 'app-detailed-view',
  templateUrl: './detailed-view.component.html',
  styleUrls: ['./detailed-view.component.css']
})
export class DetailedViewComponent implements OnInit {

  // to hold an employee
  employee: Employee = new Employee();
  // to hold a list of SIDs
  sidList: string[] = [];

  constructor(private employeeService: EmployeeService,
              private sidService: SidService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.fetchEmployee();
    this.fetchListOfSID();
  }

  fetchEmployee() {

    // get the EID from the route
    const eid: number = +this.route.snapshot.paramMap.get("eid");

    // get the employee from the backend with the EID obtained above
    this.employeeService.getOne(eid).subscribe(
      data => {

        // log the data
        console.log(data);

        // store the employee
        this.employee = data;

      }
    );

  }

  fetchListOfSID() {

    // get a list of SIDs
    this.sidService.getListOfSID().subscribe(
      data => {
        this.sidList = data;
      }
    );

  }

  onSubmit() {

    this.employeeService.update(this.employee).subscribe(
      data => {
        this.employee = data;
      }
    );

  }

  delete(eid: number) {

    this.employeeService.delete(eid).subscribe(
      data => {

        // log the data
        console.log(`deleted: ${data.name}, ${data.eid}`);

      }
    );
  }
}
