package dcatano.domain.office.finder;

import dcatano.domain.office.Office;
import dcatano.infraestructure.persistance.inmemory.office.InMemoryOfficeRepository;
import dcatano.domain.office.OfficeRepository;

import java.util.Optional;
import java.util.UUID;

public class OfficeFinder {
    private final OfficeRepository officeRepository = new InMemoryOfficeRepository();

    public Optional<Office> findByUserId(UUID uuid) {
        return officeRepository.findByEmployee(uuid);
    }
}
