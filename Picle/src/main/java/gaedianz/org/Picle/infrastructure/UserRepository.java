package gaedianz.org.Picle.infrastructure;

import gaedianz.org.Picle.domain.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

    // CREATE
    void save(User user);

    // READ

    // UPDATE

    // DELETE
}
