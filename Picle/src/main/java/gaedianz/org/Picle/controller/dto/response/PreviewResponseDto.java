package gaedianz.org.Picle.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PreviewResponseDto {
    private Long routineId;
    private Long userId;
    private String content;
    private LocalTime time;
    private LocalDate startRepeatDate;
    private Set<DayOfWeek> repeatDays;

    public static PreviewResponseDto of(Long routineId, Long userId, String content, LocalTime time, LocalDate startRepeatDate, Set<DayOfWeek> repeatDays) {
        return new PreviewResponseDto(routineId, userId, content, time, startRepeatDate, repeatDays);
    }
}