package dcatano.infraestructure.persistance.inmemory.employee;

import dcatano.employee.Employee;
import dcatano.employee.EmployeeRepository;
import dcatano.infraestructure.persistance.inmemory.InMemoryPersistence;

public class InMemoryEmployeeRepository implements EmployeeRepository {
    @Override
    public void save(Employee employee) {
        InMemoryPersistence.getEmployees().add(DBEmployee.fromDomain(employee));
    }
}
