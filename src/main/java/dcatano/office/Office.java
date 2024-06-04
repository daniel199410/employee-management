package dcatano.office;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Office {
    private final int id;
    private final String name;
    private final City city;

}
