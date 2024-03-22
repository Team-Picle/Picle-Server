package gaedianz.org.Picle.service;

import gaedianz.org.Picle.controller.dto.request.RoutineRequestDto;
import gaedianz.org.Picle.controller.dto.request.UpdateRoutineRequestDto;
import gaedianz.org.Picle.controller.dto.request.VerifyRoutineRequestDto;
import gaedianz.org.Picle.controller.dto.response.FeedResponseDto;
import gaedianz.org.Picle.controller.dto.response.PreviewResponseDto;
import gaedianz.org.Picle.controller.dto.response.RoutineResponseDto;
import gaedianz.org.Picle.controller.dto.response.VerifyRoutineResponseDto;
import gaedianz.org.Picle.domain.Routine;
import gaedianz.org.Picle.domain.User;
import gaedianz.org.Picle.exception.Error;
import gaedianz.org.Picle.exception.model.DateVerificationFailedException;
import gaedianz.org.Picle.exception.model.ImageVerificationFailedException;
import gaedianz.org.Picle.exception.model.LocationVerificationFailedException;
import gaedianz.org.Picle.exception.model.NotFoundException;
import gaedianz.org.Picle.infrastructure.RoutineRepository;
import gaedianz.org.Picle.infrastructure.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoutineService {

    private final UserRepository userRepository;
    private final RoutineRepository routineRepository;
    private final ImageVerificationService imageVerificationService;

    public List<PreviewResponseDto> getPreviews(Long userId, LocalDate date) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));

        List<Routine> previews = routineRepository.findByUserIdAndDay(userId, date.getDayOfWeek());

        // 루틴 미리보기인 지, 사용자가 조회한 날짜가 루틴 반복 시작 날짜 이후인 지
        return previews.stream()
                .filter(routine -> routine.getIsPreview() && !routine.getStartRepeatDate().isAfter(date))
                .map(this::convertToPreviewResponseDto)
                .collect(Collectors.toList());
    }

    public List<RoutineResponseDto> getByDate(Long userId, LocalDate date) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));

        List<Routine> routines = routineRepository.findByUserIdAndDate(userId, date);

        return routines.stream()
                .filter(routine -> !routine.getIsPreview())
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public List<FeedResponseDto> getMyFeeds(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));

        List<Routine> feeds = routineRepository.findByUserId(userId);

        return feeds.stream()
                .filter(routine -> routine.getIsCompleted())
                .map(routine -> convertToFeedResponseDto(routine, user))
                .collect(Collectors.toList());
    }

    public List<FeedResponseDto> getAllFeeds() {
        List<User> users = userRepository.findAll();
        List<FeedResponseDto> feeds = new ArrayList<>();

        for (User user : users) {
            List<Routine> routines = routineRepository.findByUserId(user.getId());

            feeds.addAll(routines.stream()
                    .filter(routine -> routine.getIsCompleted())
                    .map(routine -> convertToFeedResponseDto(routine, user))
                    .collect(Collectors.toList()));
        }

        return feeds;
    }

    @Transactional
    public RoutineResponseDto createPreview(Long userId, RoutineRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));

        Routine preview = Routine.newInstance(
                user,
                request.getContent(),
                request.getRegistrationImgUrl(),
                request.getTime(),
                request.getStartRepeatDate(),
                request.getRepeatDays(),
                request.getDestinationLongitude(),
                request.getDestinationLatitude(),
                false,
                true
        );
        routineRepository.save(preview);

        return convertToResponseDto(preview);
    }

    @Transactional
    public RoutineResponseDto createRoutine(Long userId, Long routineId, LocalDate date) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));

        Routine routine = routineRepository.findByIdAndUserId(routineId, userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_ROUTINE_EXCEPTION, Error.NOT_FOUND_ROUTINE_EXCEPTION.getMessage()));

        Routine newRoutine = null;

        if (routine.getIsPreview() == true) {
            newRoutine = Routine.newInstance(
                    user,
                    routineId,
                    routine.getContent(),
                    routine.getRegistrationImgUrl(),
                    date,
                    routine.getTime(),
                    routine.getStartRepeatDate(),
                    routine.getDestinationLongitude(),
                    routine.getDestinationLatitude(),
                    false,
                    false
            );

            routineRepository.save(newRoutine);
        }

        return convertToResponseDto(newRoutine);
    }

    @Transactional
    public RoutineResponseDto updatePreview(Long userId, Long routineId, UpdateRoutineRequestDto request) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));

        Routine routine = routineRepository.findByIdAndIsPreview(routineId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_ROUTINE_EXCEPTION, Error.NOT_FOUND_ROUTINE_EXCEPTION.getMessage()));

        if (request.getTime() != null){
            routine.setTime(request.getTime());
        }
        if (request.getRepeatDays() != null){
            routine.setRepeatDays(request.getRepeatDays());
        }

        routineRepository.save(routine);

        return convertToResponseDto(routine);
    }

    @Transactional
    public VerifyRoutineResponseDto verifyRoutine(Long userId, Long routineId, LocalDate date, VerifyRoutineRequestDto request) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));

        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_ROUTINE_EXCEPTION, Error.NOT_FOUND_ROUTINE_EXCEPTION.getMessage()));

        // 유저가 루틴을 수행하는 날짜가 루틴 생성 시 설정한 날짜와 동일한 지 검증
        if (routine.getDate().isEqual(date)) {
            // 유저가 루틴을 수행할 목적지의 반경 50m 내에 존재하는 지 검증
            if (isWithinDistance(request.getCurrentLongitude(), request.getCurrentLatitude(),
                    routine.getDestinationLongitude(), routine.getDestinationLatitude(), 50.0)) {

                // OpenCV를 사용하여 이미지 유사도 검증 수행
                if (imageSimilarityCheck(routine.getRegistrationImgUrl(), request.getVerifiedImgUrl()) >= 0.65) {
                    routine.setIsCompleted(true);
                    routine.setVerifiedImgUrl(request.getVerifiedImgUrl());
                    routine.setCurrentLongitude(request.getCurrentLongitude());
                    routine.setCurrentLatitude(request.getCurrentLatitude());
                    routineRepository.save(routine);
                    return VerifyRoutineResponseDto.of(
                            routineId,
                            userId,
                            routine.getContent(),
                            routine.getRegistrationImgUrl(),
                            routine.getVerifiedImgUrl(),
                            routine.getDate(),
                            routine.getTime(),
                            routine.getStartRepeatDate(),
                            routine.getDestinationLongitude(),
                            routine.getDestinationLatitude(),
                            routine.getCurrentLongitude(),
                            routine.getCurrentLatitude(),
                            routine.getIsCompleted()
                    );
                } else {
                    routine.setIsCompleted(false);
                    throw new ImageVerificationFailedException(
                            Error.IMAGE_VERIFICATION_FAILED_EXCEPTION,
                            Error.IMAGE_VERIFICATION_FAILED_EXCEPTION.getMessage()
                    );
                }
            } else {
                throw new LocationVerificationFailedException(
                        Error.LOCATION_VERIFICATION_FAILED_EXCEPTION,
                        Error.LOCATION_VERIFICATION_FAILED_EXCEPTION.getMessage()
                );
            }
        } else {
            throw new DateVerificationFailedException(
                    Error.DATE_VERIFICATION_FAILED_EXCEPTION,
                    Error.DATE_VERIFICATION_FAILED_EXCEPTION.getMessage()
            );
        }
    }

    @Transactional
    public Optional<Long> deleteRoutine(Long userId, Long routineId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));
        Routine routine = routineRepository.findByIdAndUserId(routineId, userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_ROUTINE_EXCEPTION, Error.NOT_FOUND_ROUTINE_EXCEPTION.getMessage()));

        routineRepository.delete(routine);

        return Optional.of(routineId);
    }

    private PreviewResponseDto convertToPreviewResponseDto(Routine routine) {
        return PreviewResponseDto.of(
                routine.getId(),
                routine.getContent(),
                routine.getTime(),
                routine.getStartRepeatDate(),
                routine.getRepeatDays()
        );
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
                routine.getIsCompleted(),
                routine.getIsPreview()
        );
    }

    private FeedResponseDto convertToFeedResponseDto(Routine routine, User user) {
        return FeedResponseDto.of(
                routine.getId(),
                user.getProfileImage(),
                user.getNickname(),
                routine.getVerifiedImgUrl(),
                routine.getDate()
        );
    }

    private boolean isWithinDistance(double curLatitude, double curLongitude, double desLatitude, double desLongitude, double distance) {
        double dLatitude = desLatitude - curLatitude;
        double dLongitude = desLongitude - curLongitude;

        double actualDistance = Math.sqrt(Math.pow(dLatitude, 2) + Math.pow(dLongitude, 2));

        return actualDistance <= distance;
    }

    public double imageSimilarityCheck(String imageUrl1, String imageUrl2) {
        return imageVerificationService.verifyImageSimilarity(imageUrl1, imageUrl2);
    }
}