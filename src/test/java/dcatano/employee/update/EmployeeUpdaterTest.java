package dcatano.employee.update;

import dcatano.employee.Employee;
import dcatano.employee.EmployeeRepository;
import dcatano.employee.EmployeeStatus;
import dcatano.employee.finder.FinderFilter;
import dcatano.office.City;
import dcatano.office.Office;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeUpdaterTest {
    @Test
    void shouldSendValidationErrorOnEmployeeNotFound() {
        assertEquals("No se ha encontrado el empleado", new EmployeeUpdater(new EmployeeRepository() {
            @Override
            public void save(Employee employee) {

            }

            @Override
            public Optional<Employee> findById(UUID employeeId) {
                return Optional.empty();
            }

            @Override
            public List<Employee> findAll(FinderFilter filter) {
                return List.of();
            }

            @Override
            public long countByOffice(Integer officeId) {
                return 0;
            }
        }).updatePosition(UUID.randomUUID(), "pos").getFirst());
    }

    @Test
    void shouldUpdatePosition() {
        assertTrue(new EmployeeUpdater(new EmployeeRepository() {
            @Override
            public void save(Employee employee) {

            }

            @Override
            public Optional<Employee> findById(UUID employeeId) {
                return Optional.of(new Employee(UUID.randomUUID(), "", "", LocalDate.now(), new Office(1, "", City.BEIJING), EmployeeStatus.ENABLED, 1.0));
            }

            @Override
            public List<Employee> findAll(FinderFilter filter) {
                return List.of();
            }

            @Override
            public long countByOffice(Integer officeId) {
                return 0;
            }
        }).updatePosition(UUID.randomUUID(), "pos").isEmpty());
    }
}
