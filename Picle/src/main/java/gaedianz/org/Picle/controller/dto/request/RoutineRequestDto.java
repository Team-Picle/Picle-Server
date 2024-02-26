package gaedianz.org.Picle.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoutineRequestDto {
    @NotBlank
    @Schema(description = "루틴")
    private String content;

    @NotBlank
    @Schema(description = "루틴 등록 이미지")
    private String registrationImgUrl;

    @Schema(description = "시간")
    private LocalTime time;

    @NotNull
    @Schema(description = "루틴 시작 날짜")
    private LocalDate startRepeatDate;

    @NotNull
    @Schema(description = "반복 요일")
    private Set<DayOfWeek> repeatDays;

    @NotNull
    @Schema(description = "루틴 등록 경도")
    private Double destinationLongitude;

    @NotNull
    @Schema(description = "루틴 등록 위도")
    private Double destinationLatitude;
}
