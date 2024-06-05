package dcatano.domain.office;

import java.util.Optional;
import java.util.UUID;

public interface OfficeRepository {
    Optional<Office> findOne();

    Optional<Office> findByEmployee(UUID uuid);
}
