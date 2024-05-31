package dcatano.office;

import dcatano.employee.Employee;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Office {
    private final int id;
    private final String name;
    private final City city;
    private final List<Employee> employees;

}
