package dcatano.employee.creation;

import dcatano.employee.Employee;
import dcatano.employee.EmployeeRepository;
import dcatano.employee.ValidationError;
import dcatano.infraestructure.persistance.inmemory.employee.InMemoryEmployeeRepository;
import dcatano.infraestructure.persistance.inmemory.office.InMemoryOfficeRepository;
import dcatano.office.Office;
import dcatano.office.OfficeRepository;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class EmployeeCreator {
    private final EmployeeRepository employeeRepository = new InMemoryEmployeeRepository();
    private final OfficeRepository officeRepository = new InMemoryOfficeRepository();

    public List<String> create(EmployeeCreatorDTO employeeCreatorDTO) {
        List<ValidationError> validationErrors = validEmployee(employeeCreatorDTO);
        if(!validationErrors.isEmpty()) {
            return validationErrors.stream().map(ValidationError::reason).toList();
        }
        Optional<Office> office = officeRepository.findOne();
        if(office.isEmpty()) {
            return List.of("No se han encontrado oficinas");
        }
        Employee employee = employeeCreatorDTO.toEmployee(office.get());
        employeeRepository.save(employee);
        return Collections.emptyList();
    }

    private List<ValidationError> validEmployee(EmployeeCreatorDTO employeeCreatorDTO) {
        List<ValidationError> validationErrors = new LinkedList<>();
        if(Optional.ofNullable(employeeCreatorDTO.name()).orElse("").isEmpty()) {
            validationErrors.add(new ValidationError("El nombre no puede ser vacío"));
        }
        if(Optional.ofNullable(employeeCreatorDTO.salary()).isEmpty()) {
            validationErrors.add(new ValidationError("El salario no puede ser vacío"));
        } else if (employeeCreatorDTO.salary() <= 0) {
            validationErrors.add(new ValidationError("El salario no puede ser negativo"));
        }
        return validationErrors;
    }
}
