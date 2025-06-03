package gaedianz.org.Picle.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExploreFeedResponseDto {

    private Long routineId;
    private String verifiedImgUrl;
    private String nickname;
    private String profileImage;
    private Boolean isLike;

    public static ExploreFeedResponseDto of(
            Long routineId,
            String verifiedImgUrl,
            String nickname,
            String profileImage,
            Boolean isLike
    ) {
        return new ExploreFeedResponseDto(routineId, verifiedImgUrl, nickname, profileImage, isLike);
    }
}
