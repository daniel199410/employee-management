package dcatano.domain.employee.finder;

import dcatano.domain.employee.EmployeeRepository;
import dcatano.infraestructure.persistance.inmemory.employee.InMemoryEmployeeRepository;

public class EmployeeCounter {
    private final EmployeeRepository employeeRepository = new InMemoryEmployeeRepository();

    public long findByOffice(Integer officeId) {
        return employeeRepository.countByOffice(officeId);
    }
}
