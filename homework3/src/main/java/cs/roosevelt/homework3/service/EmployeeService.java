package cs.roosevelt.homework3.service;

import cs.roosevelt.homework3.entity.Employee;
import cs.roosevelt.homework3.exception.EmployeeFoundException;
import cs.roosevelt.homework3.exception.EmployeeNotFoundException;
import cs.roosevelt.homework3.exception.NoDataFoundException;
import cs.roosevelt.homework3.exception.SupervisorNotFoundException;
import cs.roosevelt.homework3.repository.EmployeeEIDNameOnly;
import cs.roosevelt.homework3.repository.EmployeeRepository;
import cs.roosevelt.homework3.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SupervisorRepository supervisorRepository;

    /**
     * Endpoint 1.
     * Service method to get all employees from the
     * database, with optional parameters.
     * @param sid The SID of the employee.
     * @param pos The Position of the employee.
     * @return The list of employees.
     */
    public List<Employee> getEmployees(Optional<Integer> sid, Optional<String> pos) {
        // an empty list of employees.
        List<Employee> employees;

        // if sid is present in request:
        if (sid.isPresent()) {
            // if position is present in request:
            if (pos.isPresent()) {
                // add employees to list by given sid and position.
                employees = employeeRepository.findBySidAndPosition(sid.get(), pos.get());
                // if sid is present, but not position, in request:
            } else {
                // add employees to list by given sid.
                employees = employeeRepository.findBySid(sid.get());
            }
        } else {
            // if position is present, but not sid, in request:
            if (pos.isPresent()) {
                // add employees to list by given position.
                employees = employeeRepository.findByPosition(pos.get());
                // if no parameter is present in request:
            } else {
                // add all employees to list.
                employees = (List<Employee>) employeeRepository.findAll();
            }
        }
        // is the employee list empty?
        if (employees.isEmpty()) {
            throw new NoDataFoundException();
        }
        // return the list of employees.
        return employees;
    }

    /**
     * Endpoint 2.
     * Service method to get a specific employee from the database.
     * @param eid The EID of the employee to retrieve.
     * @return The employee.
     */
    public Employee getEmployee(int eid) {
        return employeeRepository.findById(eid).orElseThrow(() -> new EmployeeNotFoundException(eid));
    }

    /**
     * Endpoint 3.
     * Service method to get all employees by department from the database.
     * @param dept The department.
     * @return A list of employees by department.
     */
    public List<Employee> getEmployeesByDept(String dept) {
        // an empty list of employees.
        List<Employee> employees;
        // add employees to the list by given department.
        employees = employeeRepository.findByDepartment(dept);
        // is the employee list empty?
        if (employees.isEmpty()) {
            throw new NoDataFoundException();
        }
        // return the list of employees.
        return employees;
    }

    /**
     * Endpoint 4.
     * Service method to get the number of employees by SID from the database.
     * @param sid The SID of the employee.
     * @return The number of employees with given SID.
     */
    public Integer getEmployeeCountBySid(int sid) {
        // does the supervisor exist?
        if (!supervisorRepository.existsById(sid)) {
            throw new SupervisorNotFoundException(sid);
        }
        // if so, return employee count by sid.
        return employeeRepository.countBySid(sid);
    }

    /**
     * Endpoint 5.
     * Service method to get all employees with more than 20
     * years of service from the database.
     * @return The list of employees with yos greater than 20.
     */
    public List<EmployeeEIDNameOnly> getEmployeesByYosGreaterThan20() {
        // an empty list of employees.
        List<EmployeeEIDNameOnly> employees;
        // add employees to the list having the given yos
        employees = employeeRepository.findByYosGreaterThan20();
        // is the employee list empty?
        if (employees.isEmpty()) {
            throw new NoDataFoundException();
        }
        return employees;
    }

    /**
     * Post Endpoint.
     * Service method to add an employee to the database.
     * @param employee The employee to add.
     * @return The employee added.
     */
    public Employee addEmployee(Employee employee) {
        // does it exist already?
        if (employeeRepository.existsById(employee.getEid())) {
            // it does; throw exception.
            // addEmployee should only add new employees.
            // Use updateEmployee to change existing employee info.
            throw new EmployeeFoundException();
        }
        // else, save the employee.
        employeeRepository.save(employee);
        // return the newly added employee.
        return employeeRepository.findByEid(employee.getEid());
    }

    /**
     * Put Endpoint.
     * Service method to update an existing employee in the database.
     * @param employee The employee to update.
     * @return The updated employee.
     */
    public Employee updateEmployee(Employee employee) {
        // does it already exist?
        if (!employeeRepository.existsById(employee.getEid())) {
            // it does not; throw exception.
            // updateEmployee should only change existing employee info.
            // Use addEmployee to add a new employee.
            throw new EmployeeNotFoundException(employee.getEid());
        }
        // else, update the employee.
        employeeRepository.save(employee);
        // return the updated employee.
        return employeeRepository.findByEid(employee.getEid());
    }

    /**
     * Delete Endpoint.
     * Service method to delete an existing employee.
     * @param eid The employee to delete.
     * @return The deleted employee.
     */
    public Employee deleteEmployee(int eid) {
        // does it already exist?
        if (employeeRepository.existsById(eid)) {
            // it does; temporarily store it.
            Employee temp = employeeRepository.findByEid(eid);
            // delete the employee.
            employeeRepository.deleteById(eid);
            // return the delete employee.
            return temp;
        } else {
            // else, it does not exist.
            throw new EmployeeNotFoundException(eid);
        }
    }

    public void createHTMLEmployeeListOrderByEid() {
        System.out.println("ORDER BY EID:");
        System.out.println();
        // start of html formatting; table open tag.
        System.out.println("<table>");
        for (Employee employee : employeeRepository.findFirstThirtyEmployeesOrderByYosAsc()
        ) {
                System.out.println("<tr>");
                System.out.println("<td>" + employee.getEid() + "</td>");
                System.out.println("<td>" + employee.getName() + "</td>");
                System.out.println("<td>" + employee.getPosition() + "</td>");
                System.out.println("<td>" + employee.getYos() + "</td>");
                System.out.println("<td>" + employee.getSalary() + "</td>");
                System.out.println("<td>" + employee.getSid() + "</td>");
                System.out.println("</tr>");
        }
        // end of html formatting; table close tag.
        System.out.println("</table>");
    }

    /**
     * Homework 4 additions
     */
    public List<Employee> getAllEmployeesOrderByEidAsc() {
        return (List<Employee>) employeeRepository.findAllOrderByEidAsc();
    }

    public List<Employee> getAllEmployeesOrderByEidDesc() {
        return (List<Employee>) employeeRepository.findAllOrderByEidDesc();
    }

    public List<Employee> getAllEmployeesOrderByNameAsc() {
        return (List<Employee>) employeeRepository.findAllOrderByNameAsc();
    }

    public List<Employee> getAllEmployeesOrderByNameDesc() {
        return (List<Employee>) employeeRepository.findAllOrderByNameDesc();
    }

    public List<Employee> getAllEmployeesOrderByPositionAsc() {
        return (List<Employee>) employeeRepository.findAllOrderByPositionAsc();
    }

    public List<Employee> getAllEmployeesOrderByPositionDesc() {
        return (List<Employee>) employeeRepository.findAllOrderByPositionDesc();
    }
}
