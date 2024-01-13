package gaedianz.org.Picle.service;

import gaedianz.org.Picle.infrastructure.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public void deleteImage(Long imageId) {
        imageRepository.deleteById(imageId);
    }
}
