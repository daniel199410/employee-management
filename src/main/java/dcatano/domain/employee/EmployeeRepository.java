package dcatano.domain.employee;

import dcatano.domain.employee.finder.FinderFilter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository {
    void save(Employee employee);
    Optional<Employee> findById(UUID employeeId);
    List<Employee> findAll(FinderFilter filter);
    long countByOffice(Integer officeId);
}
