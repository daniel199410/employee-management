package dcatano.employee.creation;

import dcatano.employee.Employee;
import dcatano.office.Office;

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
