package gaedianz.org.Picle.infrastructure;

import org.springframework.data.repository.Repository;
import gaedianz.org.Picle.domain.Image;

public interface ImageRepository extends Repository<Image, Long> {
    // CREATE
    void save(Image image);

    // DELETE
    void deleteById(Long imageId);
}
