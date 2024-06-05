package dcatano.employee.creation;

import dcatano.infraestructure.persistance.inmemory.employee.InMemoryEmployeeRepository;
import dcatano.infraestructure.persistance.inmemory.office.InMemoryOfficeRepository;
import dcatano.office.City;
import dcatano.office.Office;
import dcatano.office.OfficeRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeCreatorTest {
    private final EmployeeCreator employeeCreator = new EmployeeCreator(new InMemoryEmployeeRepository(), new InMemoryOfficeRepository());
    private final EmployeeCreator employeeCreatorWithNonExistentOffice = new EmployeeCreator(new InMemoryEmployeeRepository(), new OfficeRepository() {
        @Override
        public Optional<Office> findOne() {
            return Optional.empty();
        }

        @Override
        public Optional<Office> findByEmployee(UUID uuid) {
            return Optional.empty();
        }
    });
    private final EmployeeCreator employeeCreatorWithExistentOffice = new EmployeeCreator(new InMemoryEmployeeRepository(), new OfficeRepository() {
        @Override
        public Optional<Office> findOne() {
            return Optional.of(new Office(1, "", City.BEIJING));
        }

        @Override
        public Optional<Office> findByEmployee(UUID uuid) {
            return Optional.empty();
        }
    });

    @Test
    void shouldGetInvalidNameValidation() {
        assertEquals("El nombre no puede ser vacío", employeeCreator.create(new EmployeeCreatorDTO("", "Ingeniero", 3000.0)).getFirst());
        assertEquals("El nombre no puede ser vacío", employeeCreator.create(new EmployeeCreatorDTO(null, "Ingeniero", 3000.0)).getFirst());
    }

    @Test
    void shouldValidateNameAndSalary() {
        assertEquals("El nombre no puede ser vacíoEl salario no puede ser vacío", employeeCreator.create(new EmployeeCreatorDTO("", "Ingeniero", null)).stream().reduce(String::concat).orElse(""));
    }

    @Test
    void shouldValidateSalaryGreaterThanZero() {
        assertEquals("El salario no puede ser negativo", employeeCreator.create(new EmployeeCreatorDTO("Daniel", "Ingeniero", -3000.0)).getFirst());
    }

    @Test
    void shouldNotSaveIfAnyOfficeExists() {
        assertEquals("No se han encontrado oficinas", employeeCreatorWithNonExistentOffice.create(new EmployeeCreatorDTO("Daniel", "Ingeniero", 3000.0)).getFirst());
    }

    @Test
    void shouldSaveEmployee() {
        assertTrue(employeeCreatorWithExistentOffice.create(new EmployeeCreatorDTO("Daniel", "Ingeniero", 3000.0)).isEmpty());
    }
}
