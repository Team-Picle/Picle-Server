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
    Optional<Todo> findById(Long todoId);

    // Todo 엔티티에서 user 필드 참조해서 userId와 tourId를 통해 해당 투두 조회
    @Query("SELECT t FROM Todo t WHERE t.user.id = :userId AND t.id = :todoId")
    Optional<Todo> findByUserIdAndTodoId(Long userId, Long todoId);
    List<Todo> findByUserIdAndDate(Long userId, LocalDate date);

    // UPDATE

    // DELETE
    void deleteById(Long todoId);
}
