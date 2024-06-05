package dcatano.domain.employee.finder;

import dcatano.domain.employee.EmployeeRepository;
import dcatano.infraestructure.persistance.inmemory.employee.InMemoryEmployeeRepository;

import java.util.List;

public class EmployeeFinder {
    private final EmployeeRepository employeeRepository = new InMemoryEmployeeRepository();
    public List<EmployeeFinderDTO> findAll(FinderFilter filter) {
        return employeeRepository.findAll(filter).stream().map(EmployeeFinderDTO::fomDomain).toList();
    }
}
