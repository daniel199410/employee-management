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
    EXIT("Salir"),
    ;

    private final String description;

}
