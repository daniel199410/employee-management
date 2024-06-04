package dcatano.office.finder;

import dcatano.infraestructure.persistance.inmemory.office.InMemoryOfficeRepository;
import dcatano.office.Office;
import dcatano.office.OfficeRepository;

import java.util.Optional;
import java.util.UUID;

public class OfficeFinder {
    private final OfficeRepository officeRepository = new InMemoryOfficeRepository();

    public Optional<Office> findByUserId(UUID uuid) {
        return officeRepository.findByEmployee(uuid);
    }
}
