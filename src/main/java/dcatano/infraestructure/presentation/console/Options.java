package dcatano.infraestructure.presentation.console;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Options {
    CREATE_USER("Crear usuario"),
    UPDATE_POSITION("Actualizar posición del usuario"),
    LIST_ALL_EMPLOYEES("Listar todos los empleados"),
    LIST_FILTERED_EMPLOYEES("Listar todos los empleados con filtros"),
    FIND_EMPLOYEE_OFFICE("Encontrar en qué oficina se encuentra un empleado"),
    EMPLOYEE_COUNT_IN_OFFICE("Contar empleados en una oficina"),
    EXIT("Salir"),
    ;

    private final String description;

}
