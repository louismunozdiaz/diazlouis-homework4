package cs.roosevelt.homework3.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(int eid) {
        super(String.format("Employee with EID %d not found", eid));
    }
}
