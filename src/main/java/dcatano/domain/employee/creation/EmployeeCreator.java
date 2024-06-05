package dcatano.domain.employee.creation;

import dcatano.domain.employee.Employee;
import dcatano.domain.employee.EmployeeRepository;
import dcatano.domain.employee.ValidationError;
import dcatano.domain.office.Office;
import dcatano.domain.office.OfficeRepository;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class EmployeeCreator {
    private final EmployeeRepository employeeRepository;
    private final OfficeRepository officeRepository;

    public List<String> create(EmployeeCreatorDTO employeeCreatorDTO) {
        List<ValidationError> validationErrors = validEmployee(employeeCreatorDTO);
        if(!validationErrors.isEmpty()) {
            return validationErrors.stream().map(ValidationError::reason).toList();
        }
        Optional<Office> office = officeRepository.findOne();
        if(office.isEmpty()) {
            return List.of(Messages.OFFICES_NOT_FOUND.getMessage());
        }
        Employee employee = employeeCreatorDTO.toEmployee(office.get());
        employeeRepository.save(employee);
        return Collections.emptyList();
    }

    private List<ValidationError> validEmployee(EmployeeCreatorDTO employeeCreatorDTO) {
        List<ValidationError> validationErrors = new LinkedList<>();
        if(Optional.ofNullable(employeeCreatorDTO.name()).orElse("").isEmpty()) {
            validationErrors.add(new ValidationError(Messages.INVALID_NAME.getMessage()));
        }
        if(Optional.ofNullable(employeeCreatorDTO.salary()).isEmpty()) {
            validationErrors.add(new ValidationError(Messages.INVALID_SALARY.getMessage()));
        } else if (employeeCreatorDTO.salary() <= 0) {
            validationErrors.add(new ValidationError(Messages.NEGATIVE_SALARY.getMessage()));
        }
        return validationErrors;
    }
}
