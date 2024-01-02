package gaedianz.org.Picle.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponseDto {
    private Long id;
    private String nickname;
    private String profileImage;

    public static UserResponseDto of(Long userId, String nickname, String profileImage) {
        return new UserResponseDto(userId, nickname, profileImage);
    }
}
