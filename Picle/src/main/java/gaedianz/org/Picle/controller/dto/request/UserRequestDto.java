package gaedianz.org.Picle.controller.dto.request;

import gaedianz.org.Picle.domain.SocialPlatform;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRequestDto {
    @NotBlank
    @Schema(description = "유저 닉네임")
    private String nickname;

    @NotBlank
    @Schema(description = "유저 프로필 이미지")
    private String profileImage;

    @NotNull
    @Schema(description = "유저 소셜 플랫폼")
    private SocialPlatform socialPlatform;

    @NotNull
    @Schema(description = "Access Token")
    private String accessToken;

    @NotNull
    @Schema(description = "Refresh Token")
    private String refreshToken;
}

