package gaedianz.org.Picle.infrastructure;

import gaedianz.org.Picle.domain.Routine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RoutineRepository extends Repository<Routine, Long> {

    // CREATE
    void save(Routine routine);

    // READ
    @Query("SELECT r FROM Routine r WHERE r.user.id = :userId AND r.date = :date AND r.isCompleted = true")
    List<Routine> findCompletedRoutines(Long userId, LocalDate date);
    @Query("SELECT r FROM Routine r WHERE r.user.id = :userId AND r.routineIdentifier = :routineIdentifier")
    List<Routine> findByUserIdAndRoutineIdentifier(Long userId, Long routineIdentifier);
    List<Routine> findByUserIdAndDate(Long userId, LocalDate date);

    // UPDATE

    // DELETE
    void delete(Routine routine);
}
