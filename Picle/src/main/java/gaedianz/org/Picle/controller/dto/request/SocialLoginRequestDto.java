package gaedianz.org.Picle.controller.dto.request;

import gaedianz.org.Picle.domain.SocialPlatform;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SocialLoginRequestDto {
    private SocialPlatform socialPlatform;
}