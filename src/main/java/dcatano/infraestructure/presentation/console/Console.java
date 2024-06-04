package dcatano.infraestructure.presentation.console;

import dcatano.employee.creation.EmployeeCreator;
import dcatano.employee.creation.EmployeeCreatorDTO;
import dcatano.infraestructure.presentation.Presentation;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Console implements Presentation {
    private final EmployeeCreator employeeCreator = new EmployeeCreator();

    @Override
    public void execute() {
        Optional<Options> options;
        do {
            System.out.println("Hola a tu empresa");
            options = selectOption();
            if(options.isEmpty()) {
                continue;
            }
            if (options.get() == Options.CREATE_USER) {
                presentEmployeeCreation();
            }
            if (options.get() == Options.EXIT) {
                System.out.println("Adiós");
                break;
            }
            System.out.println();
        } while (true);
    }

    private Optional<Options> selectOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
            ¿qué deseas hacer?
            1. Crear empleado
            2. Salir
            Digita el número de la acción que quieres realizar y luego haz enter""");
        try {
            return Optional.of(Options.values()[scanner.nextInt() - 1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Selecciona una opción correcta");
            return Optional.empty();
        }
    }

    private void presentEmployeeCreation() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Ingresa el nombre: ");
            String name = scanner.nextLine();
            System.out.print("Ingresa la posición: ");
            String position = scanner.nextLine();
            System.out.print("Ingresa el salario: ");
            Double salary = scanner.nextDouble();
            List<String> failedValidations = employeeCreator.create(new EmployeeCreatorDTO(name, position, salary));
            if(failedValidations.isEmpty()) {
                System.out.println("Empleado creado exitosamente");
                return;
            }
            System.out.println("No se ha podido crear el empleado, Razón: ");
            failedValidations.forEach(v -> System.out.printf("-- %s%n", v));
        } catch (InputMismatchException e) {
            System.out.println("Ingresa los datos correctos");
        }
    }
}
