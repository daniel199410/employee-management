package dcatano.infraestructure.persistance.inmemory.office;

import dcatano.infraestructure.persistance.inmemory.InMemoryPersistence;
import dcatano.office.Office;
import dcatano.office.OfficeRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class InMemoryOfficeRepository implements OfficeRepository {
    @Override
    public Optional<Office> findOne() {
        List<Office> offices = InMemoryPersistence.getOffices();
        if(offices.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(offices.get(ThreadLocalRandom.current().nextInt(0, offices.size())));
    }
}
