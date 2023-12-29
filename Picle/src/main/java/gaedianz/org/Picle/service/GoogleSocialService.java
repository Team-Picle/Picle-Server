package gaedianz.org.Picle.service;

import gaedianz.org.Picle.domain.SocialPlatform;
import gaedianz.org.Picle.domain.User;
import gaedianz.org.Picle.external.client.google.GoogleApiClient;
import gaedianz.org.Picle.external.client.google.GoogleAuthApiClient;
import gaedianz.org.Picle.external.client.google.dto.response.GoogleAccessTokenResponse;
import gaedianz.org.Picle.external.client.google.dto.response.GoogleUserResponse;
import gaedianz.org.Picle.infrastructure.UserRepository;
import gaedianz.org.Picle.service.dto.request.SocialLoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleSocialService extends SocialService{
    @Value("${google.clientId}")
    private String clientId;

    @Value("${google.clientSecret}")
    private String clientSecret;

    @Value("${google.redirectUri}")
    private String redirectUri;

    private final UserRepository userRepository;
    private final GoogleAuthApiClient googleAuthApiClient;
    private final GoogleApiClient googleApiClient;

    public Long login(SocialLoginRequest request) {

        System.out.println(clientId);

        // Authorization code로 Access Token 불러오기
        GoogleAccessTokenResponse tokenResponse = googleAuthApiClient.getOAuth2AccessToken(
                clientId,
                clientSecret,
                request.getCode(),
                "authorization_code",
                redirectUri
        );

        // Access Token으로 유저 정보 불러오기
        GoogleUserResponse userResponse = googleApiClient.getUserInformation("Bearer " + tokenResponse.getAccessToken());

        User user = User.of(
                userResponse.getName(),
                userResponse.getPicture(),
                SocialPlatform.GOOGLE,
                tokenResponse.getAccessToken(),
               "refreshToken"
        );

        userRepository.save(user);

        return user.getId();
    }
}