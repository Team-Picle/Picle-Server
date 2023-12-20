package gaedianz.org.Picle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import gaedianz.org.Picle.domain.SocialPlatform;
import gaedianz.org.Picle.domain.User;
import gaedianz.org.Picle.external.client.kakao.KakaoApiClient;
import gaedianz.org.Picle.external.client.kakao.KakaoAuthApiClient;
import gaedianz.org.Picle.external.client.kakao.dto.response.KakaoAccessTokenResponse;
import gaedianz.org.Picle.external.client.kakao.dto.response.KakaoUserResponse;
import gaedianz.org.Picle.infrastructure.UserRepository;
import gaedianz.org.Picle.service.dto.request.SocialLoginRequest;

@Service
@RequiredArgsConstructor
public class KakaoSocialService extends SocialService {

    @Value("${kakao.clientId}")
    private String clientId;

    private final UserRepository userRepository;
    private final KakaoAuthApiClient kakaoAuthApiClient;
    private final KakaoApiClient kakaoApiClient;

    @Override
    public Long login(SocialLoginRequest request) {

        System.out.println(clientId);

        // Authorization code로 Access Token 불러오기
        KakaoAccessTokenResponse tokenResponse = kakaoAuthApiClient.getOAuth2AccessToken(
                "authorization_code",
                clientId,
                "http://localhost:8080/kakao/callback",
                request.getCode()
        );

        // Access Token으로 유저 정보 불러오기
        KakaoUserResponse userResponse = kakaoApiClient.getUserInformation("Bearer " + tokenResponse.getAccessToken());

        User user = User.of(
                userResponse.getKakaoAccount().getProfile().getNickname(),
                userResponse.getKakaoAccount().getProfile().getProfileImageUrl(),
                SocialPlatform.KAKAO,
                tokenResponse.getAccessToken(),
                tokenResponse.getRefreshToken()
        );

        userRepository.save(user);

        return user.getId();
    }
}