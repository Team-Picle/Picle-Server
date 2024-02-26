package gaedianz.org.Picle.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateTodoRequestDto {

    @Schema(description = "할 일")
    private String content;

    @Schema(description = "날짜")
    private LocalDate date;

    @Schema(description = "완료 여부")
    private Boolean isCompleted;
}