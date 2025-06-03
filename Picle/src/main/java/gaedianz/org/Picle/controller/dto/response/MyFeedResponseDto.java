package gaedianz.org.Picle.controller.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MyFeedResponseDto {

    private Long routineId;
    private String verifiedImgUrl;
    private LocalDate date;
    private Long likeCount;

    public static MyFeedResponseDto of(
            Long routineId,
            LocalDate date,
            String verifiedImgUrl,
            Long likeCount
    ) {
        return new MyFeedResponseDto(routineId, verifiedImgUrl, date, likeCount);
    }
}
