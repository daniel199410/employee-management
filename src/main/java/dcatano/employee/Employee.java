package dcatano.employee;

import dcatano.office.Office;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class Employee {
    private final UUID id;
    private final String name;
    private final String position;
    private final LocalDate hiringDate;
    private final Office office;
    private final EmployeeStatus status;
    private final Double salary;

    public Employee(String name, String position, Double salary) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.position = position;
        this.hiringDate = LocalDate.now();
        this.office = null;
        this.status = EmployeeStatus.ENABLED;
        this.salary = salary;
    }


}
