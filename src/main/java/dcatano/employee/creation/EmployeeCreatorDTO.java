package dcatano.employee.creation;

import dcatano.employee.Employee;

public record EmployeeCreatorDTO(String name, String position, Double salary) {
    public Employee toEmployee() {
        return new Employee(
            name,
            position,
            salary
        );
    }
}
