package dcatano.infraestructure.presentation.console;

import dcatano.employee.creation.EmployeeCreator;
import dcatano.employee.creation.EmployeeCreatorDTO;
import dcatano.employee.finder.EmployeeFinder;
import dcatano.employee.finder.EmployeeFinderDTO;
import dcatano.employee.finder.FinderFilter;
import dcatano.employee.update.EmployeeUpdater;
import dcatano.infraestructure.presentation.Presentation;
import dcatano.office.Office;
import dcatano.employee.finder.EmployeeCounter;
import dcatano.office.finder.OfficeFinder;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class Console implements Presentation {
    private final EmployeeCreator employeeCreator = new EmployeeCreator();
    private final EmployeeUpdater employeeUpdater = new EmployeeUpdater();
    private final EmployeeFinder employeeFinder = new EmployeeFinder();
    private final OfficeFinder officeFinder = new OfficeFinder();
    private final EmployeeCounter employeeCounter = new EmployeeCounter();

    @Override
    public void execute() {
        Optional<Options> option;
        System.out.println("Hola!");
        do {
            option = selectOption();
            if(option.isEmpty()) {
                continue;
            }
            switch (option.get()) {
                case Options.CREATE_USER -> presentEmployeeCreation();
                case Options.UPDATE_POSITION -> presentEmployeeUpdate();
                case Options.LIST_ALL_EMPLOYEES -> presentListAllEmployees(null);
                case Options.LIST_FILTERED_EMPLOYEES -> presentListFilteredEmployees();
                case Options.FIND_EMPLOYEE_OFFICE -> presentEmployeeOffice();
                case Options.EMPLOYEE_COUNT_IN_OFFICE -> presentOfficeEmployeeCount();
                case Options.EXIT -> {
                    exit();
                    return;
                }
            }
            System.out.println();
        } while (true);
    }

    private void exit() {
        System.out.println("Adiós");
    }

    private void presentOfficeEmployeeCount() {
        System.out.println("Ingrese el id de la oficina");
        Scanner scanner = new Scanner(System.in);
        try {
            Integer officeId = scanner.nextInt();
            long employeeCount = employeeCounter.findByOffice(officeId);
            System.out.printf("La oficina tiene %d empleados%n", employeeCount);
        } catch (InputMismatchException e) {
            System.err.println("El id debe ser un número");
        }
    }

    private void presentEmployeeOffice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa el id del empleado: ");
        UUID uuid = UUID.fromString(scanner.nextLine());
        Optional<Office> office = officeFinder.findByUserId(uuid);
        if(office.isEmpty()) {
            System.err.print("No se encontrado la oficina o el usuario");
            return;
        }
        System.out.println("-- Oficina: --");
        System.out.printf("Nombre: %s%n", office.get().name());
        System.out.printf("Ciudad: %s%n", office.get().city());
    }

    private void presentListFilteredEmployees() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Filtrar por id? (Y o N): ");
        boolean byId = scanner.nextLine().equalsIgnoreCase("Y");
        UUID uuid = null;
        if(byId) {
            System.out.print("Ingrese el id a filtrar: ");
            uuid = UUID.fromString(scanner.nextLine());
        }
        System.out.print("Filtrar por posición? (Y o N): ");
        boolean byPosition = scanner.nextLine().equalsIgnoreCase("Y");
        String position = null;
        if(byPosition) {
            System.out.print("Ingrese la posición a filtrar: ");
            position = scanner.nextLine();
        }
        presentListAllEmployees(new FinderFilter(uuid, position));
    }

    private void presentListAllEmployees(FinderFilter finderFilter) {
        System.out.print("A continuación se presentan los empleados registrados: ");
        List<EmployeeFinderDTO> employees = employeeFinder.findAll(finderFilter);
        if(employees.isEmpty()) {
            System.err.println("-- No se han encontrado empleados --");
            return;
        }
        employees.forEach(e -> {
            System.out.println();
            System.out.printf("id: %s%n", e.id());
            System.out.printf("Nombre: %s%n", e.name());
            System.out.printf("Fecha de contratación: %s%n", e.hiringDate());
            System.out.printf("Posición: %s%n", e.position());
            System.out.printf("Salario: $%s%n", e.salary());
        });
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
            System.err.println("Selecciona una opción correcta");
            return Optional.empty();
        } catch (InputMismatchException e) {
            System.err.println("Ingresa los datos correctos");
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
