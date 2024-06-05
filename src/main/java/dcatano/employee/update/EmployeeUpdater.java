package dcatano.employee.update;

import dcatano.employee.Employee;
import dcatano.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class EmployeeUpdater {
    private final EmployeeRepository employeeRepository;

    public List<String> updatePosition(UUID employeeId, String position) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        optionalEmployee.ifPresent(foundEmployee -> {
            Employee toUpdateEmployee = new Employee(
                foundEmployee.getId(),
                foundEmployee.getName(),
                position,
                foundEmployee.getHiringDate(),
                foundEmployee.getOffice(),
                foundEmployee.getStatus(),
                foundEmployee.getSalary()
            );
            employeeRepository.save(toUpdateEmployee);
        });
        if(optionalEmployee.isPresent()) {
            return Collections.emptyList();
        }
        return Collections.singletonList("No se ha encontrado el empleado");
    }
}
