package gaedianz.org.Picle.infrastructure;

import gaedianz.org.Picle.domain.Todo;
import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends Repository<Todo, Long> {

    // CREATE
    void save(Todo todo);

    // READ
    Optional<Todo> findById(Long todoId);
    List<Todo> findByUserIdAndDate(Long userId, LocalDate date);

    // UPDATE

    // DELETE
}
