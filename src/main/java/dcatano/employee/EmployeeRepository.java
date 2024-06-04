package dcatano.employee;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository {
    void save(Employee employee);
    Optional<Employee> findById(UUID employeeId);
}
