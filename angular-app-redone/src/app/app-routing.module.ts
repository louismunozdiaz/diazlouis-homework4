import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {EmployeeListComponent} from './components/employee-list/employee-list.component';
import {DetailedViewComponent} from "./components/detailed-view/detailed-view.component";
import {AddEmployeeComponent} from "./components/add-employee/add-employee.component";

const routes: Routes = [
  {
    path: 'addEmployee',
    component: AddEmployeeComponent
  },
  {
    path: 'employee/:eid',
    component: DetailedViewComponent
  },
  {
    path: 'employees',
    component: EmployeeListComponent
  },
  {
    path: '',
    redirectTo: '/employees',
    pathMatch: 'full'
  },
  {
    path: '**',
    redirectTo: '/employees',
    pathMatch: 'full'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
