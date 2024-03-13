package gaedianz.org.Picle.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RoutineResponseDto {
    private Long routineId;
    private Long routineIdentifier;
    private Long userId;
    private String content;
    private String registrationImgUrl;
    private LocalDate date;
    private LocalTime time;
    private LocalDate startRepeatDate;
    private Set<DayOfWeek> repeatDays;
    private Double destinationLongitude;
    private Double destinationLatitude;
    private Boolean isCompleted;
    private Boolean isPreview;

    public static RoutineResponseDto of(Long routineId, Long routineIdentifier, Long userId, String content,
                                        String registrationImgUrl, LocalDate date, LocalTime time,
                                        LocalDate startRepeatDate, Set<DayOfWeek> repeatDays,
                                        Double destinationLongitude, Double destinationLatitude,
                                        Boolean isCompleted, Boolean isPreview){
        return new RoutineResponseDto(routineId, routineIdentifier, userId, content, registrationImgUrl, date, time,
                startRepeatDate, repeatDays, destinationLongitude, destinationLatitude, isCompleted, isPreview);
    }
}