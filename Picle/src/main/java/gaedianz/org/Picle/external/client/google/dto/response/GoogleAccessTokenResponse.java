package gaedianz.org.Picle.external.client.google.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GoogleAccessTokenResponse {

    private String accessToken;

    public static GoogleAccessTokenResponse of(String accessToken) {
        return new GoogleAccessTokenResponse(accessToken);
    }
}