package dcatano.infraestructure.persistance.inmemory.employee;

import dcatano.employee.Employee;
import dcatano.employee.EmployeeRepository;
import dcatano.infraestructure.persistance.inmemory.InMemoryPersistence;

import java.util.Optional;
import java.util.UUID;

public class InMemoryEmployeeRepository implements EmployeeRepository {
    @Override
    public void save(Employee employee) {
        DBEmployee dbEmployee = DBEmployee.fromDomain(employee);
        System.out.println(dbEmployee.id());
        InMemoryPersistence.getEmployees().remove(dbEmployee);
        InMemoryPersistence.getEmployees().add(dbEmployee);
    }

    @Override
    public Optional<Employee> findById(UUID employeeId) {
        return InMemoryPersistence
            .getEmployees()
            .stream()
            .filter(e -> e.id().equals(employeeId))
            .findFirst()
            .map(DBEmployee::toDomain);
    }
}
