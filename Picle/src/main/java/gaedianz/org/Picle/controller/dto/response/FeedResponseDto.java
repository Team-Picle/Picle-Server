package gaedianz.org.Picle.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FeedResponseDto {
    private Long routineId;
    private String profileImage;
    private String nickname;
    private String verifiedImgUrl;
    private LocalDate date;

    public static FeedResponseDto of(Long routineId, String profileImage, String nickname, String verifiedImgUrl, LocalDate date) {
        return new FeedResponseDto(routineId, profileImage, nickname, verifiedImgUrl, date);
    }
}
