package gaedianz.org.Picle.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VerifyRoutineRequestDto {
    @NotNull
    @Schema(description = "검증 이미지")
    private String verifiedImgUrl;

    @NotNull
    @Schema(description = "사용자 현재 경도")
    private Double currentLongitude;

    @NotNull
    @Schema(description = "사용자 현재 위도")
    private Double currentLatitude;
}
