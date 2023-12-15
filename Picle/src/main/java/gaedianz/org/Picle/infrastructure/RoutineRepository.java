package gaedianz.org.Picle.infrastructure;

import gaedianz.org.Picle.domain.Routine;
import org.springframework.data.repository.Repository;

public interface RoutineRepository extends Repository<Routine, Long> {

    // CREATE
    void save(Routine routine);

    // READ

    // UPDATE

    // DELETE
}
