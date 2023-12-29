package gaedianz.org.Picle.external.client.google.dto.response;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GoogleUserResponse {
    private String name;
    private String picture;
}
