package cs.roosevelt.homework3.exception;

public class SupervisorNotFoundException extends RuntimeException{

    public SupervisorNotFoundException(int sid) {
        super(String.format("Supervisor with SID %d not found", sid));
    }
}
