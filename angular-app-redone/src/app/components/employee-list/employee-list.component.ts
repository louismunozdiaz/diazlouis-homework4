import { Component, OnInit } from '@angular/core';
import {Employee} from '../../model/employee';
import {EmployeeService} from '../../services/employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  // to hold a list of employees
  employees: Employee[];
  // to determine if list is in asc/desc order
  isAscending: boolean = true;

  // to decide which attribute to sort by
  isEidClicked: boolean = false;
  isNameClicked: boolean = false;
  isPositionClicked: boolean = false;

  constructor(private employeeService: EmployeeService) { }

  ngOnInit(): void {

    // fetch all the employees
    this.fetchAllEmployees();

  }


  fetchAllEmployees() {

    // which attribute should we sort by?
    if (this.isEidClicked) {

      this.handleEmployeeListByEID();

    } else if (this.isNameClicked) {

      this.handleEmployeeListByName();

    } else if (this.isPositionClicked) {

      this.handleEmployeeListByPosition();

    } else {

      // the default state; must return all employees, unordered
      this.employeeService.getAll().subscribe(
        data => {
          this.employees = data;
        }
      );

    }
    // endIf

  }

  handleEmployeeListByEID() {

    // should we sort in asc order?
    if (this.isEidClicked === true && this.isAscending === true) {

      // yes, we should
      this.employeeService.getAllOrderByEidAsc().subscribe(
        data => {
          this.employees = data;
        }
      );

    } else {

      // no, sort in descending order
      this.employeeService.getAllOrderByEidDesc().subscribe(
        data => {
          this.employees = data;
        }
      );

    }
    // endIf

  }

  refresh() {
    this.ngOnInit();
  }

  private handleEmployeeListByName() {

    // should we sort in asc order?
    if (this.isNameClicked === true && this.isAscending === true) {

      // yes, we should
      this.employeeService.getAllOrderByNameAsc().subscribe(
        data => {
          this.employees = data;
        }
      );

    } else {

      // no, sort in descending order
      this.employeeService.getAllOrderByNameDesc().subscribe(
        data => {
          this.employees = data;
        }
      );

    }
    // endIf

  }

  private handleEmployeeListByPosition() {

    // should we sort in ascending order?
    if (this.isPositionClicked === true && this.isAscending === true) {

      // yes, we should
      this.employeeService.getAllOrderByPosAsc().subscribe(
        data => {
          this.employees = data;
        }
      );

    } else {

      // no, sort in descending order
      this.employeeService.getAllOrderByPosDesc().subscribe(
        data => {
          this.employees = data;
        }
      );

    }
    // endIf

  }

  changeSort(isEidClicked: boolean, isNameClicked: boolean, isPositionClicked: boolean) {

    // on click change the sort order
    this.isAscending = !this.isAscending;

    // set the boolean variables
    this.isEidClicked = isEidClicked;
    this.isNameClicked = isNameClicked;
    this.isPositionClicked = isPositionClicked;

    // update the employees array
    this.fetchAllEmployees();

  }

}
