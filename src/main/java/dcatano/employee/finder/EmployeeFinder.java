package dcatano.employee.finder;

import dcatano.employee.EmployeeRepository;
import dcatano.infraestructure.persistance.inmemory.employee.InMemoryEmployeeRepository;

import java.util.List;

public class EmployeeFinder {
    private final EmployeeRepository employeeRepository = new InMemoryEmployeeRepository();
    public List<EmployeeFinderDTO> findAll() {
        return employeeRepository.findAll().stream().map(EmployeeFinderDTO::fomDomain).toList();
    }
}
