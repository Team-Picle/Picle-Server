package gaedianz.org.Picle.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoRequestDto {
    @NotBlank
    @Schema(description = "할 일")
    private String content;

    @NotNull
    @Schema(description = "날짜")
    private LocalDate date;
}
