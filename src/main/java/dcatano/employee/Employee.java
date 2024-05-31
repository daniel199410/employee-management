package dcatano.employee;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
public class Employee {
    private final String name;
    private final String position;
    private final LocalDate hiringDate;
    private final EmployeeStatus status;
    private final Double salary;
}
