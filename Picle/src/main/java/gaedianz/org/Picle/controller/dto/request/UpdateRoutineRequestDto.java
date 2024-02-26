package gaedianz.org.Picle.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateRoutineRequestDto {
    @Schema(description = "시간")
    private LocalTime time;

    @Schema(description = "반복 요일")
    private Set<DayOfWeek> repeatDays;
}
