package dcatano.domain.employee.update;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
enum Messages {
    EMPLOYEE_NOT_FOUND("No se ha encontrado el empleado"),
    ;

    private final String message;
}
