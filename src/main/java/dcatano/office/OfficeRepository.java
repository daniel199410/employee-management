package dcatano.office;

import java.util.Optional;

public interface OfficeRepository {
    Optional<Office> findOne();
}
