package dcatano.infraestructure.persistance.inmemory;

import dcatano.employee.EmployeeStatus;
import dcatano.infraestructure.persistance.inmemory.employee.DBEmployee;
import dcatano.office.City;
import dcatano.office.Office;
import lombok.Getter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class InMemoryPersistence {
    @Getter
    static final List<Office> offices = new LinkedList<>();
    @Getter
    static final Set<DBEmployee> employees = new HashSet<>();

    public static void initDB() {
        offices.add(new Office(1, "name", City.BEIJING));
        offices.add(new Office(1, "name", City.BEIJING));
        offices.add(new Office(1, "name", City.BEIJING));
        offices.add(new Office(1, "name", City.BEIJING));
        employees.add(new DBEmployee(UUID.randomUUID(), "a", "1", LocalDate.now(), null, EmployeeStatus.ENABLED, 3.0));
        employees.add(new DBEmployee(UUID.randomUUID(), "a", "1", LocalDate.now(), null, EmployeeStatus.ENABLED, 3.0));
        employees.add(new DBEmployee(UUID.randomUUID(), "a", "2", LocalDate.now(), null, EmployeeStatus.ENABLED, 3.0));
        employees.add(new DBEmployee(UUID.randomUUID(), "a", "2", LocalDate.now(), null, EmployeeStatus.ENABLED, 3.0));
    }
}
