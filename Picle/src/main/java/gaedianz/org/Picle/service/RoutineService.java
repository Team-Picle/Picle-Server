package gaedianz.org.Picle.service;

import gaedianz.org.Picle.controller.dto.request.RoutineRequestDto;
import gaedianz.org.Picle.controller.dto.response.RoutineResponseDto;
import gaedianz.org.Picle.domain.Routine;
import gaedianz.org.Picle.domain.User;
import gaedianz.org.Picle.exception.Error;
import gaedianz.org.Picle.exception.model.NotFoundException;
import gaedianz.org.Picle.infrastructure.RoutineRepository;
import gaedianz.org.Picle.infrastructure.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RoutineService {
    private final UserRepository userRepository;
    private final RoutineRepository routineRepository;
    private final ImageService imageService;

    @Transactional
    public List<RoutineResponseDto> createRoutine(Long userId, RoutineRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));

        Set<DayOfWeek> repeatDays = request.getRepeatDays();
        LocalDate startDate = request.getStartRepeatDate();
        LocalDate endDate = startDate.plusYears(1);

        List<Routine> createdRoutines = new ArrayList<>();

        // 특정 날짜부터 1년 동안의 선택한 요일에 해당하는 루틴 생성
        while (startDate.isBefore(endDate)) {
            for (DayOfWeek selectedDay : repeatDays) {
                if (startDate.getDayOfWeek() == selectedDay) {
                    Routine routine = Routine.newInstance(
                            user,
                            request.getContent(),
                            request.getRegistrationImgUrl(),
                            startDate,
                            request.getTime(),
                            request.getStartRepeatDate(),
                            repeatDays,
                            request.getDestinationLongitude(),
                            request.getDestinationLatitude(),
                            false
                    );
                    routineRepository.save(routine);
                    createdRoutines.add(routine);
                    break;
                }
            }
            startDate = startDate.plusDays(1);
        }

        Long firstRoutineId = createdRoutines.get(0).getId();
        for (Routine routine : createdRoutines) {
            routine.setRoutineIdentifier(firstRoutineId);
        }

        return Collections.singletonList(convertToResponseDto(createdRoutines.get(0)));

    }

    @Transactional
    public Optional<Long> deleteRoutine(Long userId, Long routineIdentifier) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));

        List<Routine> routinesToDelete = routineRepository.findByUserIdAndRoutineIdentifier(userId, routineIdentifier);

        if (routinesToDelete.isEmpty()) {
            throw new NotFoundException(Error.NOT_FOUND_ROUTINE_EXCEPTION, Error.NOT_FOUND_ROUTINE_EXCEPTION.getMessage());
        }

        // 현재 날짜 이후의 루틴이거나 완료하지 않은 루틴을 삭제
        for (Routine routine : routinesToDelete) {
            if (!routine.getDate().isBefore(LocalDate.now()) || !routine.getIsCompleted()) {
                routine.getVerifiedImgUrl().forEach(image -> imageService.deleteImage(image.getId()));
                routine.getRepeatDays().clear();
                routineRepository.delete(routine);
            }
        }
        return Optional.of(routineIdentifier);
    }

    private RoutineResponseDto convertToResponseDto(Routine routine) {
        return RoutineResponseDto.of(
                routine.getId(),
                routine.getRoutineIdentifier(),
                routine.getUser().getId(),
                routine.getContent(),
                routine.getRegistrationImgUrl(),
                routine.getDate(),
                routine.getTime(),
                routine.getStartRepeatDate(),
                routine.getRepeatDays(),
                routine.getDestinationLongitude(),
                routine.getDestinationLatitude(),
                routine.getIsCompleted()
        );
    }
}
