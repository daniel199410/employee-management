package dcatano.infraestructure.persistance.inmemory;

import dcatano.employee.EmployeeStatus;
import dcatano.infraestructure.persistance.inmemory.employee.DBEmployee;
import dcatano.office.City;
import dcatano.office.Office;
import lombok.Getter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class InMemoryPersistence {
    @Getter
    static final Set<Office> offices = new HashSet<>();
    @Getter
    static final Set<DBEmployee> employees = new HashSet<>();

    public static void initDB() {
        offices.add(new Office(1, "name", City.BEIJING));
        offices.add(new Office(2, "name", City.MEDELLIN));
        offices.add(new Office(3, "name", City.PARIS));
        offices.add(new Office(4, "name", City.SAO_PAULO));
        employees.add(new DBEmployee(UUID.fromString("1c17bf0f-d8db-43b6-8705-255d38c4d97a"), "a", "1", LocalDate.now(), new Office(1, "name", City.BEIJING), EmployeeStatus.ENABLED, 3.0));
        employees.add(new DBEmployee(UUID.fromString("1c17bf0f-d8db-43b6-8705-255d38c4d97e"), "a", "1", LocalDate.now(), new Office(1, "name", City.BEIJING), EmployeeStatus.ENABLED, 3.0));
        employees.add(new DBEmployee(UUID.fromString("1c17bf0f-d8db-43b6-8705-255d38c4d97f"), "a", "1", LocalDate.now(), new Office(1, "name", City.BEIJING), EmployeeStatus.ENABLED, 3.0));
        employees.add(new DBEmployee(UUID.fromString("1c17bf0f-d8db-43b6-8705-255d38c4d97b"), "a", "1", LocalDate.now(), new Office(2, "name", City.MEDELLIN), EmployeeStatus.ENABLED, 3.0));
        employees.add(new DBEmployee(UUID.fromString("1c17bf0f-d8db-43b6-8705-255d38c4d98a"), "a", "1", LocalDate.now(), new Office(2, "name", City.MEDELLIN), EmployeeStatus.ENABLED, 3.0));
        employees.add(new DBEmployee(UUID.fromString("1c17bf0f-d8db-43b6-8705-255d38c4d99a"), "a", "1", LocalDate.now(), new Office(2, "name", City.MEDELLIN), EmployeeStatus.ENABLED, 3.0));
        employees.add(new DBEmployee(UUID.fromString("1c17bf0f-d8db-43b6-8705-255d38c4d91a"), "a", "1", LocalDate.now(), new Office(2, "name", City.MEDELLIN), EmployeeStatus.ENABLED, 3.0));
        employees.add(new DBEmployee(UUID.fromString("1c17bf0f-d8db-43b6-8705-255d38c4d97c"), "a", "2", LocalDate.now(), new Office(3, "name", City.PARIS), EmployeeStatus.ENABLED, 3.0));
        employees.add(new DBEmployee(UUID.fromString("1c17bf0f-d8db-43b6-8705-255d38c4d97d"), "a", "2", LocalDate.now(), new Office(4, "name", City.SAO_PAULO), EmployeeStatus.ENABLED, 3.0));
    }
}
