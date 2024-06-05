package dcatano.infraestructure.persistance.inmemory.employee;

import dcatano.employee.Employee;
import dcatano.employee.EmployeeRepository;
import dcatano.employee.finder.FinderFilter;
import dcatano.infraestructure.persistance.inmemory.InMemoryPersistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

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

    @Override
    public List<Employee> findAll(FinderFilter finderFilter) {
        return InMemoryPersistence
            .getEmployees()
            .stream()
            .filter(getFilter(finderFilter))
            .map(DBEmployee::toDomain)
            .toList();
    }

    @Override
    public long countByOffice(Integer officeId) {
        return InMemoryPersistence.getEmployees().stream().filter(e -> e.office().id().equals(officeId)).count();
    }

    private Predicate<? super DBEmployee> getFilter(FinderFilter filter) {
        return (Predicate<DBEmployee>) dbEmployee -> {
            if (filter == null) {
                return true;
            }
            boolean valid = true;
            if (filter.id() != null) {
                valid = filter.id().equals(dbEmployee.id());
            }
            if (filter.position() != null) {
                valid = filter.position().equals(dbEmployee.position());
            }
            return valid;
        };
    }
}
