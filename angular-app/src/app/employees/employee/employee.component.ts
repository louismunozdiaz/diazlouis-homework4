import { Component, OnInit } from '@angular/core';
import { Supervisor } from 'src/app/model/supervisor';
import { MatDialogRef } from '@angular/material/dialog'

import { EmployeeService } from 'src/app/services/employee.service';
import { SupervisorService } from 'src/app/services/supervisor.service';

/**
 * This is the employee form component.
 */

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  // used to fill the 'select SID' option only.
  supervisors: Supervisor[];

  constructor(public service: EmployeeService, 
    public supervisorService: SupervisorService,
    public dialogRef: MatDialogRef<EmployeeComponent>) { }

  ngOnInit(): void {
    this.service.getEmployees().subscribe();
    this.getSupervisorData();
  }

  // Logic for clicking the submit button.
  onSubmit() {
    if (this.service.form.valid) {
      this.service.addEmployee(this.service.form.value).subscribe(
        data => {
          alert("Employee created successfully");
        }
      );
      this.service.form.reset();
      this.service.initializeFormGroup();
      this.onClose();
    }
  }

  // Logic for the close button.
  onClose() {
    this.service.form.reset();
    this.service.initializeFormGroup();
    this.dialogRef.close();
  }

  // used to fill the 'select SID' option only.
  getSupervisorData() {
    this.supervisorService.getSupervisors().subscribe(
      data => {
        this.supervisors = data;
      }
    );
  }

}
