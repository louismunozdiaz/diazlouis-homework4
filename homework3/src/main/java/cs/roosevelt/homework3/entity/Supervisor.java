package cs.roosevelt.homework3.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "SUPERVISORS")
@Data
public class Supervisor {
    @Id
    int sid;

    @NotBlank
    String name;

    @NotBlank
    String department;
}
