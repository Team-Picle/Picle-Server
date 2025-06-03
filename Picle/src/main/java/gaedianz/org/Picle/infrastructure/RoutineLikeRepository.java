package gaedianz.org.Picle.infrastructure;

import gaedianz.org.Picle.domain.RoutineLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoutineLikeRepository extends JpaRepository<RoutineLike, Long> {

    long countByRoutineId(Long routineId);

    Optional<RoutineLike> findByUserIdAndRoutineId(Long userId, Long routineId);

    void deleteByRoutineId(Long routineId);

    void deleteByUserIdAndRoutineId(Long userId, Long routineId);
}
