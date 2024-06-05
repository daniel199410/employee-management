package dcatano.domain.employee.creation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
enum Messages {
    INVALID_NAME("El nombre no puede ser vacío"),
    INVALID_SALARY("El salario no puede ser vacío"),
    NEGATIVE_SALARY("El salario no puede ser negativo"),
    OFFICES_NOT_FOUND("No se han encontrado oficinas"),
    ;

    private final String message;
}
