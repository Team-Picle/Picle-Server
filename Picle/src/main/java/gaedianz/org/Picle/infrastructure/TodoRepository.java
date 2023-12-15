package gaedianz.org.Picle.infrastructure;

import gaedianz.org.Picle.domain.Todo;
import org.springframework.data.repository.Repository;

public interface TodoRepository extends Repository<Todo, Long> {

    // CREATE
    void save(Todo todo);

    // READ

    // UPDATE

    // DELETE
}
