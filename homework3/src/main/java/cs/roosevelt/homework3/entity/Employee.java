package cs.roosevelt.homework3.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name = "EMPLOYEES")
@Data
public class Employee {

    @Id
    @Min(value = 10000, message = "eid: must be a 5 digit number")
    @Max(value = 99999, message = "eid: must be a 5 digit number")
    private int eid;

    @NotBlank(message = "name: must not be blank")
    @Size(max = 30, message = "name: must be less than {max} characters")
    private String name;

    @NotBlank(message = "position: must not be blank")
    @Size(max = 30, message = "position: must be less than {max} characters")
    private String position;

    @Positive(message = "yos: must be greater than or equal to 0")
    private int yos;

    @Positive(message = "salary: must be greater than or equal to 0")
    private double salary;

    @Min(value = 10000, message = "sid: must be a 5 digit number")
    @Max(value = 99999, message = "sid: must be a 5 digit number")
    private int sid;
}
