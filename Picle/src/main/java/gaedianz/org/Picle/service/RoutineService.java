package gaedianz.org.Picle.service;

import gaedianz.org.Picle.controller.dto.request.RoutineRequestDto;
import gaedianz.org.Picle.controller.dto.response.RoutineResponseDto;
import gaedianz.org.Picle.domain.Image;
import gaedianz.org.Picle.domain.Routine;
import gaedianz.org.Picle.domain.User;
import gaedianz.org.Picle.exception.Error;
import gaedianz.org.Picle.exception.model.NotFoundException;
import gaedianz.org.Picle.infrastructure.RoutineRepository;
import gaedianz.org.Picle.infrastructure.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoutineService {
    private final UserRepository userRepository;
    private final RoutineRepository routineRepository;

    @Transactional
    public RoutineResponseDto createRoutine(Long userId, RoutineRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));

        Routine routine = Routine.newInstance(
                user,
                request.getContent(),
                request.getRegistrationImgUrl(),
                request.getDate(),
                request.getTime(),
                request.getStartRepeatDate(),
                request.getRepeatDays(),
                request.getDestinationLongitude(),
                request.getDestinationLatitude(),
                request.getIsCompleted()
        );
        routineRepository.save(routine);

        return RoutineResponseDto.of(routine.getId(), userId, routine.getContent(), routine.getRegistrationImgUrl(),
                routine.getDate(), routine.getTime(), routine.getStartRepeatDate(), routine.getRepeatDays(), routine.getDestinationLongitude(), routine.getDestinationLatitude(),
                routine.getIsCompleted());
    }

    @Transactional
    public Optional<Long> deleteRoutine(Long userId, Long routineId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));
        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_ROUTINE_EXCEPTION, Error.NOT_FOUND_ROUTINE_EXCEPTION.getMessage()));

        routine.removeAllImages();
        routine.getRepeatDays().clear();
        routineRepository.delete(routine);

        return Optional.of(routineId);
    }
}
