package cs.roosevelt.homework3.repository;

import cs.roosevelt.homework3.entity.Supervisor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupervisorRepository extends CrudRepository<Supervisor, Integer> {
    /* Endpoint 6. */
    List<Supervisor> findBySid(int sid);

    @Query(value = "SELECT * FROM SUPERVISORS ORDER BY SID", nativeQuery = true)
    Iterable<Supervisor> getAllSid();
}
