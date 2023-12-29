package gaedianz.org.Picle.external.client.google;

import gaedianz.org.Picle.external.client.google.dto.response.GoogleAccessTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name = "googleAuthApiClient", url = "https://www.googleapis.com")
public interface GoogleAuthApiClient {
    @PostMapping(value = "/oauth2/v4/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    GoogleAccessTokenResponse getOAuth2AccessToken(
            @RequestParam("client_id") String clientId,
            @RequestParam("client_secret") String clientSecret,
            @RequestParam("code") String code,
            @RequestParam("grant_type") String grantType,
            @RequestParam("redirect_uri") String redirectUri

    );
}

