package cs.roosevelt.homework3.exception;

public class EmployeeFoundException extends RuntimeException{

    public EmployeeFoundException() {
        super("Employee already exists");
    }
}
