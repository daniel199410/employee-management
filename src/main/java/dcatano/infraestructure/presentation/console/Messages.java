package dcatano.infraestructure.presentation.console;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
enum Messages {
    BYE("Adiós"),
    CITY("Ciudad: %s%n"),
    EMPLOYEE_CREATED("Empleado creado exitosamente"),
    EMPLOYEE_NOT_CREATED("No se ha podido crear el empleado, Razón: "),
    EMPLOYEES_NOT_FOUND("-- No se han encontrado empleados --"),
    EMPLOYEES_UPDATED("Empleado actualizado exitosamente"),
    EMPLOYEES_NOT_UPDATED("No se ha podido actualizar el empleado, Razón: "),
    ENTER_ACTION("Digita el número de la acción que quieres realizar y luego haz enter"),
    ENTER_CORRECT_DATA("Ingresa los datos correctos"),
    ENTER_EMPLOYEE_ID("Ingresa el id del empleado: "),
    ENTER_ID_FILTER("Ingrese el id a filtrar: "),
    ENTER_NAME("Ingresa el nombre: "),
    ENTER_NEW_POSITION("Ingresa la nueva posición: "),
    ENTER_OFFICE_ID("Ingrese el id de la oficina"),
    ENTER_POSITION("Ingresa la posición: "),
    ENTER_POSITION_FILTER("Ingrese la posición a filtrar: "),
    ENTER_SALARY("Ingresa el salario: "),
    HEADER("¿qué deseas hacer?"),
    FILTER_BY_ID("Filtrar por id? (Y o N): "),
    FILTER_BY_POSITION("Filtrar por posición? (Y o N): "),
    HELLO("Hola!"),
    HIRING_DATE("Fecha de contratación: %s%n"),
    ID("id: %s%n"),
    INVALID_OFFICE_ID("El id debe ser un número"),
    INVALID_ID("El valor ingresado no es un id válido"),
    NAME("Nombre: %s%n"),
    OFFICE("-- Oficina: --"),
    OFFICE_HAS_N_EMPLOYEE("La oficina tiene %d empleados%n"),
    OFFICE_OR_USER_NOT_FOUND("No se encontrado la oficina o el usuario"),
    POSITION("Posición: %s%n"),
    REGISTERED_EMPLOYEES("A continuación se presentan los empleados registrados: "),
    SALARY("Salario: $%s%n"),
    SELECT_CORRECT_OPTION("Selecciona una opción correcta"),
    ;

    private final String message;
}
