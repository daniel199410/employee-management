package dcatano.infraestructure.persistance.inmemory.office;

import dcatano.domain.employee.Employee;
import dcatano.domain.employee.EmployeeRepository;
import dcatano.infraestructure.persistance.inmemory.InMemoryPersistence;
import dcatano.infraestructure.persistance.inmemory.employee.InMemoryEmployeeRepository;
import dcatano.domain.office.Office;
import dcatano.domain.office.OfficeRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class InMemoryOfficeRepository implements OfficeRepository {
    private final EmployeeRepository employeeRepository = new InMemoryEmployeeRepository();

    @Override
    public Optional<Office> findOne() {
        Set<Office> offices = InMemoryPersistence.getOffices();
        if(offices.isEmpty()) {
            return Optional.empty();
        }
        return offices.stream().filter(o -> o.id().equals(ThreadLocalRandom.current().nextInt(0, offices.size()))).findFirst();
    }

    @Override
    public Optional<Office> findByEmployee(UUID id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.flatMap(value -> InMemoryPersistence.getOffices().stream().filter(o -> o.id().equals(value.getOffice().id())).findFirst());
    }
}
