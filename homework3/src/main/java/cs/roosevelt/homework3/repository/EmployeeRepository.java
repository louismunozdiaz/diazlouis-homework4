package cs.roosevelt.homework3.repository;

import cs.roosevelt.homework3.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    /* Get employee by EID */
    Employee findByEid(int eid);

    /* Endpoint 1. */
    List<Employee> findBySid(int sid);
    List<Employee> findByPosition(String position);
    List<Employee> findBySidAndPosition(int sid, String position);

    /* Endpoint 3. */
    @Query(value = "SELECT * FROM EMPLOYEES INNER JOIN SUPERVISORS ON EMPLOYEES.SID = SUPERVISORS.SID WHERE DEPARTMENT = ?1", nativeQuery = true)
    List<Employee> findByDepartment(String dept);

    /* Endpoint 4. */
    @Query(value = "SELECT COUNT(*) FROM EMPLOYEES WHERE SID = ?1", nativeQuery = true)
    Integer countBySid(int sid);

    /* Endpoint 5. */
    @Query(value = "SELECT EID, NAME FROM EMPLOYEES WHERE YOS > 20", nativeQuery = true)
    List<EmployeeEIDNameOnly> findByYosGreaterThan20();

    @Query(value = "SELECT * FROM EMPLOYEES ORDER BY EID", nativeQuery = true)
    Iterable<Employee> findFirstThirtyEmployeesOrderByEidAsc();

    @Query(value = "SELECT * FROM EMPLOYEES ORDER BY NAME", nativeQuery = true)
    Iterable<Employee> findFirstThirtyEmployeesOrderByNameAsc();

    @Query(value = "SELECT * FROM EMPLOYEES ORDER BY POSITION", nativeQuery = true)
    Iterable<Employee> findFirstThirtyEmployeesOrderByPositionAsc();

    @Query(value = "SELECT * FROM EMPLOYEES ORDER BY YOS", nativeQuery = true)
    Iterable<Employee> findFirstThirtyEmployeesOrderByYosAsc();

    @Query(value = "SELECT * FROM EMPLOYEES ORDER BY SALARY", nativeQuery = true)
    Iterable<Employee> findFirstThirtyEmployeesOrderBySalaryAsc();

    @Query(value = "SELECT * FROM EMPLOYEES ORDER BY SID", nativeQuery = true)
    Iterable<Employee> findFirstThirtyEmployeesOrderBySidAsc();
}
