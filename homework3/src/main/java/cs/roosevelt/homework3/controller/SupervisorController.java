package cs.roosevelt.homework3.controller;

import cs.roosevelt.homework3.entity.Supervisor;
import cs.roosevelt.homework3.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class SupervisorController {
    @Autowired
    SupervisorService supervisorService;

    /**
     * Endpoint 6.
     *
     * @param sid
     * @return
     */
    @GetMapping(value = {"/supervisors", "/supervisors/{sid}"})
    public List<Supervisor> getSupervisors(@PathVariable(required = false) Optional<Integer> sid) {
        return supervisorService.getSupervisors(sid);
    }

    @GetMapping(value = {"/supervisors/list"})
    public List<Integer>  getSupervisorsSid() {
        return supervisorService.getSupervisorsSid();
    }
}
