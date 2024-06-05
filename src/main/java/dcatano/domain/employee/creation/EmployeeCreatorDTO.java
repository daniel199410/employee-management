package dcatano.domain.employee.creation;

import dcatano.domain.employee.Employee;
import dcatano.domain.office.Office;

public record EmployeeCreatorDTO(String name, String position, Double salary) {
    public Employee toEmployee(Office office) {
        return new Employee(
            name,
            office,
            position,
            salary
        );
    }
}
