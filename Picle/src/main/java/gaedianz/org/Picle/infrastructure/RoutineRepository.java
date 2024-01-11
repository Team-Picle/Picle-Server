package gaedianz.org.Picle.infrastructure;

import gaedianz.org.Picle.domain.Routine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoutineRepository extends Repository<Routine, Long> {

    // CREATE
    void save(Routine routine);

    // READ
    Optional<Routine> findById(Long routineId);

    // Routine 엔티티에서 user 필드 참조해서 userId와 routineId를 통해 해당 투두 조회
    @Query("SELECT r FROM Routine r WHERE r.user.id = :userId AND r.id = :routineId")
    Optional<Routine> findByUserIdAndRoutineId(Long userId, Long routineId);
    List<Routine> findByUserIdAndDate(Long userId, LocalDate date);
    @Query("SELECT r FROM Routine r WHERE r.user.id = :userId AND r.date = :date AND r.isCompleted = true")
    List<Routine> findCompletedRoutines(Long userId, LocalDate date);

    // UPDATE

    // DELETE
    void delete(Routine routine);
}
