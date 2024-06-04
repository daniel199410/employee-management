package dcatano.infraestructure.persistance.inmemory.employee;

import dcatano.employee.Employee;
import dcatano.employee.EmployeeStatus;
import dcatano.office.Office;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public record DBEmployee(UUID id, String name, String position, LocalDate hiringDate, Office office,
                         EmployeeStatus status, Double salary) {

    public static DBEmployee fromDomain(Employee employee) {
        return new DBEmployee(
            employee.getId(),
            employee.getName(),
            employee.getPosition(),
            employee.getHiringDate(),
            employee.getOffice(),
            employee.getStatus(),
            employee.getSalary()
        );
    }

    public Employee toDomain() {
        return new Employee(
            this.id(),
            this.name(),
            this.position(),
            this.hiringDate(),
            this.office(),
            this.status(),
            this.salary()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DBEmployee that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
