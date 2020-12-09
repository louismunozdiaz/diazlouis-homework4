package cs.roosevelt.homework3.controller;

import cs.roosevelt.homework3.repository.EmployeeEIDNameOnly;
import cs.roosevelt.homework3.service.EmployeeService;
import cs.roosevelt.homework3.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * Endpoint 1.
     * Web request controller to get all employees, with optional parameters.
     * @param sid The SID search parameter.
     * @param pos The Position search parameter.
     * @return A list of employees.
     */
    @GetMapping(value = {"/employees", "/{sid}/employees", "/employees/{pos}", "/{sid}/employees/{pos}"})
    public List<Employee> getEmployees(@PathVariable(required = false) Optional<Integer> sid,
                                       @PathVariable(required = false) Optional<String> pos) {
        return employeeService.getEmployees(sid, pos);
    }

    /**
     * Endpoint 2.
     * Web request controller to get an employee with a specific EID
     * @param eid The EID of the employee.
     * @return A specific employee.
     */
    @GetMapping(value = {"/employee/byeid/{eid}"})
    public Employee getEmployee(@PathVariable int eid) {
        return employeeService.getEmployee(eid);
    }

    /**
     * Endpoint 3.
     * Web request controller to get all employees in a given department.
     * @param dept The department.
     * @return A list of employees from the given department.
     */
    @GetMapping(value = {"/employees/bydepartment/{dept}"})
    public List<Employee> getEmployeesByDept(@PathVariable String dept) {
        return employeeService.getEmployeesByDept(dept);
    }

    /**
     * Endpoint 4.
     * Web request controller to get a count of employees with given sid.
     * @param sid The SID of the employee.
     * @return The employee count with given sid.
     */
    @GetMapping(value = {"/employees/count/bysid/{sid}"})
    public Integer countEmployeesBySid(@PathVariable int sid) {
        return employeeService.getEmployeeCountBySid(sid);
    }

    /**
     * Endpoint 5.
     * Web request controller to get all employees with
     * years of service greater than 20.
     * @return A list of employees with yos greater than 20.
     */
    @GetMapping(value = {"/employees/byserviceyears"})
    public List<EmployeeEIDNameOnly> getEmployeeByYosGreaterThan20() {
        return employeeService.getEmployeesByYosGreaterThan20();
    }

    /**
     * Post Endpoint.
     * Web request controller to add an employee.
     * @param employee The employee to add.
     * @return The employee added.
     */
    @PostMapping(value = {"/employees"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Employee addEmployee(@RequestBody @Valid Employee employee) {
        return employeeService.addEmployee(employee);
    }

    /**
     * Put Endpoint.
     * Web request controller to update an employee.
     * @param employee The employee to update.
     * @return The updated employee.
     */
    @PutMapping(value = {"/employees"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Employee updateEmployee(@RequestBody @Valid Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    /**
     * Delete Endpoint.
     * Web request controller to delete an employee with given eid.
     * @param eid The EID of the employee to delete.
     * @return The deleted employee.
     */
    @DeleteMapping(value = {"/employee/delete/byeid/{eid}"})
    public Employee deleteEmployee(@PathVariable int eid) {
        return employeeService.deleteEmployee(eid);
    }

    @GetMapping(value = {"/employees/list"})
    public void createHTMLEmployeeList() {
        employeeService.createHTMLEmployeeListOrderByEid();
    }

    /**
     * Homework 4 additions
     */
    @GetMapping(value = {"/employees/sortedbyeidasc"})
    public List<Employee> getAllEmployeesOrderByEidAsc() {
        return this.employeeService.getAllEmployeesOrderByEidAsc();
    }

    @GetMapping(value = {"/employees/sortedbyeiddesc"})
    public List<Employee> getAllEmployeesOrderByEidDesc() {
        return this.employeeService.getAllEmployeesOrderByEidDesc();
    }

    @GetMapping(value = {"/employees/sortedbynameasc"})
    public List<Employee> getAllEmployeesOrderByNameAsc() {
        return this.employeeService.getAllEmployeesOrderByNameAsc();
    }

    @GetMapping(value = {"/employees/sortedbynamedesc"})
    public List<Employee> getAllEmployeesOrderByNameDesc() {
        return this.employeeService.getAllEmployeesOrderByNameDesc();
    }

    @GetMapping(value = {"/employees/sortedbyposasc"})
    public List<Employee> getAllEmployeesOrderByPosAsc() {
        return this.employeeService.getAllEmployeesOrderByPositionAsc();
    }

    @GetMapping(value = {"/employees/sortedbyposdesc"})
    public List<Employee> getAllEmployeesOrderByPosDesc() {
        return this.employeeService.getAllEmployeesOrderByPositionDesc();
    }
}
