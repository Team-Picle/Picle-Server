package gaedianz.org.Picle.infrastructure;

import gaedianz.org.Picle.domain.Routine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoutineRepository extends Repository<Routine, Long> {

    // CREATE
    void save(Routine routine);

    // READ
    Optional<Routine> findById(Long routineId);
    @Query("SELECT r FROM Routine r WHERE r.user.id = :userId AND r.id = :routineId")
    Optional<Routine> findByIdAndUserId(Long routineId, Long userId);
    @Query("SELECT r FROM Routine r WHERE r.user.id = :userId AND :repeatDay MEMBER OF r.repeatDays")
    List<Routine> findByUserIdAndDay(Long userId, DayOfWeek repeatDay);
    @Query("SELECT r FROM Routine r WHERE r.user.id = :userId AND r.date = :date")
    List<Routine> findByUserIdAndDate(Long userId, LocalDate date);
    @Query("SELECT r FROM Routine r WHERE r.id = :routineId AND r.isPreview = true")
    Optional<Routine> findByIdAndIsPreview(Long routineId);
    List<Routine> findByUserId(Long userId);

    // UPDATE

    // DELETE
    void delete(Routine routine);
}