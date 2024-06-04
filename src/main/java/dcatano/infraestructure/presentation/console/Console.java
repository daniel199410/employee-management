package dcatano.infraestructure.presentation.console;

import dcatano.employee.creation.EmployeeCreator;
import dcatano.employee.creation.EmployeeCreatorDTO;
import dcatano.employee.update.EmployeeUpdater;
import dcatano.infraestructure.presentation.Presentation;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class Console implements Presentation {
    private final EmployeeCreator employeeCreator = new EmployeeCreator();
    private final EmployeeUpdater employeeUpdater = new EmployeeUpdater();

    @Override
    public void execute() {
        Optional<Options> options;
        System.out.println("Hola a tu empresa");
        do {
            options = selectOption();
            if(options.isEmpty()) {
                continue;
            }
            if (options.get() == Options.CREATE_USER) {
                presentEmployeeCreation();
            }
            if (options.get() == Options.UPDATE_POSITION) {
                presentEmployeeUpdate();
            }
            if (options.get() == Options.EXIT) {
                System.out.println("Adiós");
                break;
            }
            System.out.println();
        } while (true);
    }

    private void presentEmployeeUpdate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa el id del empleado: ");
        try {
            UUID uuid = UUID.fromString(scanner.nextLine());
            System.out.print("Ingresa la nueva posición: ");
            String position = scanner.nextLine();
            List<String> failedValidations = employeeUpdater.updatePosition(uuid, position);
            if (failedValidations.isEmpty()) {
                System.out.println("Empleado actualizado exitosamente");
                return;
            }
            System.err.println("No se ha podido actualizar el empleado, Razón: ");
            failedValidations.forEach(v -> System.err.printf("-- %s%n", v));
        } catch (IllegalArgumentException e) {
            System.err.println("El valor ingresado no es un id válido");
        }

    }

    private Optional<Options> selectOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿qué deseas hacer?");
        for (Options value : Options.values()) {
            System.out.printf("%d. %s%n", value.ordinal() + 1, value.getDescription());
        }
        System.out.println("Digita el número de la acción que quieres realizar y luego haz enter");
        try {
            return Optional.of(Options.values()[scanner.nextInt() - 1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Selecciona una opción correcta");
            return Optional.empty();
        } catch (InputMismatchException e) {
            System.out.println("Ingresa los datos correctos");
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
            if (failedValidations.isEmpty()) {
                System.out.println("Empleado creado exitosamente");
                return;
            }
            System.err.println("No se ha podido crear el empleado, Razón: ");
            failedValidations.forEach(v -> System.err.printf("-- %s%n", v));
        } catch (InputMismatchException e) {
            System.err.println("Ingresa los datos correctos");
        }
    }
}
