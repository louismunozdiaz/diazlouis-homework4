import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations'
import { MaterialModule } from 'src/app/material/material.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EmployeesComponent } from './employees/employees.component';
import { EmployeeComponent } from './employees/employee/employee.component';
import { EmployeeService } from 'src/app/services/employee.service';
import { EmployeeListComponent } from './employees/employee-list/employee-list.component';
import { SupervisorService } from './services/supervisor.service';
import { EmployeeUpdateComponent } from './employees/employee-update/employee-update.component';

@NgModule({
  declarations: [
    AppComponent,
    EmployeesComponent,
    EmployeeComponent,
    EmployeeListComponent,
    EmployeeUpdateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [EmployeeService, SupervisorService],
  bootstrap: [AppComponent],
  entryComponents: [
    EmployeeComponent,
    EmployeeUpdateComponent
  ]
})
export class AppModule { }
