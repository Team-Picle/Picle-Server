package gaedianz.org.Picle.controller.dto.response;

import gaedianz.org.Picle.domain.RepeatDay;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.TreeSet;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RoutineResponseDto {
    private Long routineId;
    private Long userId;
    private String content;
    private String registrationImgUrl;
    private LocalDate date;
    private LocalTime time;
    private LocalDate startRepeatDate;
    private Set<RepeatDay> repeatDays;
    private Double destinationLongitude;
    private Double destinationLatitude;
    private Boolean isCompleted;

    public static RoutineResponseDto of(Long routineId, Long userId, String content,
                                        String registrationImgUrl, LocalDate date, LocalTime time,
                                        LocalDate startRepeatDate, Set<RepeatDay> repeatDays,
                                        Double destinationLongitude, Double destinationLatitude, Boolean isCompleted){
        // repeatDays를 원하는 순서로 정렬
        TreeSet<RepeatDay> sortedRepeatDays = new TreeSet<>(repeatDays);

        return new RoutineResponseDto(routineId, userId, content,
                registrationImgUrl, date, time, startRepeatDate, sortedRepeatDays,
                destinationLongitude, destinationLatitude, isCompleted);
    }
}