package dcatano.domain.employee.finder;

import dcatano.domain.employee.Employee;

import java.time.LocalDate;

public record EmployeeFinderDTO(String id, String name, LocalDate hiringDate, String position, Double salary) {
    public static EmployeeFinderDTO fomDomain(Employee employee) {
        return new EmployeeFinderDTO(
            employee.getId().toString(),
            employee.getName(),
            employee.getHiringDate(),
            employee.getPosition(),
            employee.getSalary()
        );
    }
}
