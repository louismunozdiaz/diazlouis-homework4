package cs.roosevelt.homework3.repository;

import cs.roosevelt.homework3.entity.Supervisor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupervisorRepository extends CrudRepository<Supervisor, Integer> {

    /* Endpoint 6. */
    List<Supervisor> findBySid(int sid);

    /**
     * Homework 4 Redo
     * @return
     */
    @Query(value = "SELECT SID FROM SUPERVISORS ORDER BY SID", nativeQuery = true)
    List<String> getAllSid();

}
