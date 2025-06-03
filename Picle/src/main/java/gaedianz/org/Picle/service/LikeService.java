package gaedianz.org.Picle.service;

import gaedianz.org.Picle.domain.Routine;
import gaedianz.org.Picle.domain.RoutineLike;
import gaedianz.org.Picle.domain.User;
import gaedianz.org.Picle.exception.Error;
import gaedianz.org.Picle.exception.model.ConflictException;
import gaedianz.org.Picle.exception.model.NotFoundException;
import gaedianz.org.Picle.infrastructure.RoutineLikeRepository;
import gaedianz.org.Picle.infrastructure.RoutineRepository;
import gaedianz.org.Picle.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final RoutineLikeRepository likeRepository;
    private final RoutineRepository routineRepository;
    private final UserRepository userRepository;

    @Transactional
    public void likeRoutine(Long userId, Long routineId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(
                        Error.NOT_FOUND_USER_EXCEPTION,
                        Error.NOT_FOUND_USER_EXCEPTION.getMessage()
                ));

        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new NotFoundException(
                        Error.NOT_FOUND_ROUTINE_EXCEPTION,
                        Error.NOT_FOUND_ROUTINE_EXCEPTION.getMessage()
                ));

        if (!Boolean.TRUE.equals(routine.getIsCompleted())) {
            throw new ConflictException(
                    Error.REQUEST_VALIDATION_EXCEPTION,
                    Error.REQUEST_VALIDATION_EXCEPTION.getMessage()
            );
        }

        boolean alreadyLiked = likeRepository.findByUserIdAndRoutineId(userId, routineId).isPresent();
        if (alreadyLiked) {
            throw new ConflictException(
                    Error.CONFLICT_ROUTINE_LIKE_EXCEPTION,
                    Error.CONFLICT_ROUTINE_LIKE_EXCEPTION.getMessage()
            );
        }

        RoutineLike like = RoutineLike.of(user, routine);
        likeRepository.save(like);
    }

    @Transactional
    public void unlikeRoutine(Long userId, Long routineId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(
                        Error.NOT_FOUND_USER_EXCEPTION,
                        Error.NOT_FOUND_USER_EXCEPTION.getMessage()
                ));

        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new NotFoundException(
                        Error.NOT_FOUND_ROUTINE_EXCEPTION,
                        Error.NOT_FOUND_ROUTINE_EXCEPTION.getMessage()
                ));

        if (!Boolean.TRUE.equals(routine.getIsCompleted())) {
            throw new ConflictException(
                    Error.REQUEST_VALIDATION_EXCEPTION,
                    Error.REQUEST_VALIDATION_EXCEPTION.getMessage()
            );
        }

        likeRepository.findByUserIdAndRoutineId(userId, routineId)
                .orElseThrow(() -> new NotFoundException(
                        Error.NOT_FOUND_LIKE_EXCEPTION,
                        Error.NOT_FOUND_LIKE_EXCEPTION.getMessage()
                ));

        likeRepository.deleteByUserIdAndRoutineId(userId, routineId);
    }

    @Transactional(readOnly = true)
    public long countLikes(Long routineId) {
        routineRepository.findById(routineId)
                .orElseThrow(() -> new NotFoundException(
                        Error.NOT_FOUND_ROUTINE_EXCEPTION,
                        Error.NOT_FOUND_ROUTINE_EXCEPTION.getMessage()
                ));

        return likeRepository.countByRoutineId(routineId);
    }

    @Transactional(readOnly = true)
    public boolean hasUserLiked(Long userId, Long routineId) {
        return likeRepository.findByUserIdAndRoutineId(userId, routineId).isPresent();
    }
}
