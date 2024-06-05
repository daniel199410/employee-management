package dcatano.employee.update;

import dcatano.domain.employee.Employee;
import dcatano.domain.employee.EmployeeRepository;
import dcatano.domain.employee.EmployeeStatus;
import dcatano.domain.employee.finder.FinderFilter;
import dcatano.domain.employee.update.EmployeeUpdater;
import dcatano.domain.office.City;
import dcatano.domain.office.Office;
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
