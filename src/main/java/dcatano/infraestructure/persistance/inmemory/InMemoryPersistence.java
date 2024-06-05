package dcatano.infraestructure.persistance.inmemory;

import dcatano.infraestructure.persistance.inmemory.employee.DBEmployee;
import dcatano.domain.office.City;
import dcatano.domain.office.Office;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

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
    }
}
