package gaedianz.org.Picle.controller.dto.response;

import gaedianz.org.Picle.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@AllArgsConstructor
@Getter
public class VerifyRoutineResponseDto {
    @NotNull
    private final Long id;

    @NotNull
    private final Long userId;

    @NotBlank
    private final String content;

    @NotBlank
    private final String registrationImgUrl;

    @NotBlank
    private final String verifiedImgUrl;

    @NotNull
    private final LocalDate date;

    @NotNull
    private final LocalTime time;

    @NotNull
    private final LocalDate startRepeatDate;

    @NotNull
    private final Double destinationLongitude;

    @NotNull
    private final Double destinationLatitude;

    @NotNull
    private final Double currentLongitude;

    @NotNull
    private final Double currentLatitude;

    @NotNull
    private final Boolean isCompleted;

    public static VerifyRoutineResponseDto of(Long routineId, Long userId, String content,
                                              String registrationImgUrl, String verifiedImgUrl,
                                              LocalDate date, LocalTime time, LocalDate startRepeatDate,
                                              Double destinationLongitude, Double destinationLatitude,
                                              Double currentLongitude, Double currentLatitude, Boolean isCompleted){
        return new VerifyRoutineResponseDto(routineId, userId, content, registrationImgUrl, verifiedImgUrl, date, time,
                startRepeatDate, destinationLongitude, destinationLatitude, currentLongitude, currentLatitude, isCompleted);
    }
}