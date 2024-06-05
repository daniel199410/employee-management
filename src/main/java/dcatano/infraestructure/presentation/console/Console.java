package dcatano.infraestructure.presentation.console;

import dcatano.employee.EmployeeRepository;
import dcatano.employee.creation.EmployeeCreator;
import dcatano.employee.creation.EmployeeCreatorDTO;
import dcatano.employee.finder.EmployeeFinder;
import dcatano.employee.finder.EmployeeFinderDTO;
import dcatano.employee.finder.FinderFilter;
import dcatano.employee.update.EmployeeUpdater;
import dcatano.infraestructure.persistance.inmemory.employee.InMemoryEmployeeRepository;
import dcatano.infraestructure.persistance.inmemory.office.InMemoryOfficeRepository;
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
    EmployeeRepository employeeRepository = new InMemoryEmployeeRepository();
    private final EmployeeCreator employeeCreator = new EmployeeCreator(employeeRepository, new InMemoryOfficeRepository());
    private final EmployeeUpdater employeeUpdater = new EmployeeUpdater(employeeRepository);
    private final EmployeeFinder employeeFinder = new EmployeeFinder();
    private final OfficeFinder officeFinder = new OfficeFinder();
    private final EmployeeCounter employeeCounter = new EmployeeCounter();

    @Override
    public void execute() {
        Optional<Options> option;
        System.out.println(Messages.HELLO.getMessage());
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
        System.out.println(Messages.BYE.getMessage());
    }

    private void presentOfficeEmployeeCount() {
        System.out.println(Messages.ENTER_OFFICE_ID.getMessage());
        Scanner scanner = new Scanner(System.in);
        try {
            Integer officeId = scanner.nextInt();
            long employeeCount = employeeCounter.findByOffice(officeId);
            System.out.printf(Messages.OFFICE_HAS_N_EMPLOYEE.getMessage(), employeeCount);
        } catch (InputMismatchException e) {
            System.err.println(Messages.INVALID_OFFICE_ID.getMessage());
        }
    }

    private void presentEmployeeOffice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(Messages.ENTER_EMPLOYEE_ID.getMessage());
        UUID uuid = UUID.fromString(scanner.nextLine());
        Optional<Office> office = officeFinder.findByUserId(uuid);
        if(office.isEmpty()) {
            System.err.print(Messages.OFFICE_OR_USER_NOT_FOUND.getMessage());
            return;
        }
        System.out.println(Messages.OFFICE.getMessage());
        System.out.printf(Messages.NAME.getMessage(), office.get().name());
        System.out.printf(Messages.CITY.getMessage(), office.get().city());
    }

    private void presentListFilteredEmployees() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(Messages.FILTER_BY_ID.getMessage());
        boolean byId = scanner.nextLine().equalsIgnoreCase("Y");
        UUID uuid = null;
        if(byId) {
            System.out.print(Messages.ENTER_ID_FILTER.getMessage());
            uuid = UUID.fromString(scanner.nextLine());
        }
        System.out.print(Messages.FILTER_BY_POSITION.getMessage());
        boolean byPosition = scanner.nextLine().equalsIgnoreCase("Y");
        String position = null;
        if(byPosition) {
            System.out.print(Messages.ENTER_POSITION_FILTER.getMessage());
            position = scanner.nextLine();
        }
        presentListAllEmployees(new FinderFilter(uuid, position));
    }

    private void presentListAllEmployees(FinderFilter finderFilter) {
        System.out.print(Messages.REGISTERED_EMPLOYEES.getMessage());
        List<EmployeeFinderDTO> employees = employeeFinder.findAll(finderFilter);
        if(employees.isEmpty()) {
            System.err.println(Messages.EMPLOYEES_NOT_FOUND.getMessage());
            return;
        }
        employees.forEach(e -> {
            System.out.println();
            System.out.printf(Messages.ID.getMessage(), e.id());
            System.out.printf(Messages.NAME.getMessage(), e.name());
            System.out.printf(Messages.HIRING_DATE.getMessage(), e.hiringDate());
            System.out.printf(Messages.POSITION.getMessage(), e.position());
            System.out.printf(Messages.SALARY.getMessage(), e.salary());
        });
    }

    private void presentEmployeeUpdate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(Messages.ENTER_EMPLOYEE_ID.getMessage());
        try {
            UUID uuid = UUID.fromString(scanner.nextLine());
            System.out.print(Messages.ENTER_NEW_POSITION.getMessage());
            String position = scanner.nextLine();
            List<String> failedValidations = employeeUpdater.updatePosition(uuid, position);
            if (failedValidations.isEmpty()) {
                System.out.println(Messages.EMPLOYEES_UPDATED.getMessage());
                return;
            }
            System.err.println(Messages.EMPLOYEES_NOT_UPDATED.getMessage());
            failedValidations.forEach(v -> System.err.printf("-- %s%n", v));
        } catch (IllegalArgumentException e) {
            System.err.println(Messages.INVALID_ID.getMessage());
        }

    }

    private Optional<Options> selectOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Messages.HEADER.getMessage());
        for (Options value : Options.values()) {
            System.out.printf("%d. %s%n", value.ordinal() + 1, value.getDescription());
        }
        System.out.println(Messages.ENTER_ACTION.getMessage());
        try {
            return Optional.of(Options.values()[scanner.nextInt() - 1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println(Messages.SELECT_CORRECT_OPTION.getMessage());
            return Optional.empty();
        } catch (InputMismatchException e) {
            System.err.println(Messages.ENTER_CORRECT_DATA.getMessage());
            return Optional.empty();
        }
    }

    private void presentEmployeeCreation() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print(Messages.ENTER_NAME.getMessage());
            String name = scanner.nextLine();
            System.out.print(Messages.ENTER_POSITION.getMessage());
            String position = scanner.nextLine();
            System.out.print(Messages.ENTER_SALARY.getMessage());
            Double salary = scanner.nextDouble();
            List<String> failedValidations = employeeCreator.create(new EmployeeCreatorDTO(name, position, salary));
            if (failedValidations.isEmpty()) {
                System.out.println(Messages.EMPLOYEE_CREATED.getMessage());
                return;
            }
            System.err.println(Messages.EMPLOYEE_NOT_CREATED.getMessage());
            failedValidations.forEach(v -> System.err.printf("-- %s%n", v));
        } catch (InputMismatchException e) {
            System.err.println(Messages.ENTER_CORRECT_DATA.getMessage());
        }
    }
}
