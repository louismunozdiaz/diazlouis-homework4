package cs.roosevelt.homework3.service;

import cs.roosevelt.homework3.entity.Supervisor;
import cs.roosevelt.homework3.exception.NoDataFoundException;
import cs.roosevelt.homework3.exception.SupervisorNotFoundException;
import cs.roosevelt.homework3.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupervisorService {
    @Autowired
    SupervisorRepository supervisorRepository;

    /**
     * Endpoint 6.
     *
     * @param sid
     * @return
     */
    public List<Supervisor> getSupervisors(Optional<Integer> sid) {
        // an empty list of supervisors.
        List<Supervisor> supervisors;
        // if sid is present in request:
        if (sid.isPresent()) {
            // does the supervisor with given sid exist?
            if (!supervisorRepository.existsById(sid.get())) {
                // it does not; throw exception.
                throw new SupervisorNotFoundException(sid.get());
            } else {
                // add supervisor to the list with given sid.
                supervisors = supervisorRepository.findBySid(sid.get());
            }
            // if sid is not present in request:
        } else {
            // add all supervisors to the list.
            supervisors = (List<Supervisor>) supervisorRepository.findAll();
        }
        // is the list of supervisors empty?
        if (supervisors.isEmpty()) {
            throw new NoDataFoundException();
        }
        // return the list of supervisors.
        return supervisors;
    }

    public List<Integer> getSupervisorsSid() {
        return (ArrayList) supervisorRepository.findAll();
    }
}
