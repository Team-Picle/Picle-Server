package gaedianz.org.Picle.infrastructure;

import gaedianz.org.Picle.domain.User;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.List;

public interface UserRepository extends Repository<User, Long> {

    // CREATE
    void save(User user);

    // READ
    List<User> findAll();
    Optional<User> findById(Long userId);
    boolean existsByClientKey(String clientKey);

    // UPDATE

    // DELETE
}
