package gaedianz.org.Picle.infrastructure;

import gaedianz.org.Picle.domain.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends Repository<Todo, Long> {

    // CREATE
    void save(Todo todo);

    // READ
    List<Todo> findByUserIdAndDate(Long userId, LocalDate date);
    @Query("SELECT t FROM Todo t WHERE t.id = :todoId AND t.user.id = :userId")
    Optional<Todo> findByIdAndUserId(Long todoId, Long userId);

    // UPDATE

    // DELETE
    void delete(Todo todo);
}