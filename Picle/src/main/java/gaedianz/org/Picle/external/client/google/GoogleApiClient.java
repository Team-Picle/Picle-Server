package gaedianz.org.Picle.external.client.google;
import gaedianz.org.Picle.external.client.google.dto.response.GoogleUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "googleApiClient", url = "https://www.googleapis.com")
public interface GoogleApiClient {
    @GetMapping(value = "/oauth2/v3/userinfo", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    GoogleUserResponse getUserInformation(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken);
}
