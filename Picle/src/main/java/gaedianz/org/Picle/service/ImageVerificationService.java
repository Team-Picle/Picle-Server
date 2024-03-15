package gaedianz.org.Picle.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ImageVerificationService {
    public double verifyImageSimilarity(String imageUrl1, String imageUrl2) {
        try {
            String flaskServerUrl = "http://13.209.238.192:5000/verify-image-similarity";

            // HTTP 요청 바디 설정
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // JSON으로 요청 데이터 생성
            String requestBody = "{\"image_url1\": \"" + imageUrl1 + "\", \"image_url2\": \"" + imageUrl2 + "\"}";

            // HTTP 요청 보내기
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(flaskServerUrl, HttpMethod.POST, requestEntity, String.class);

            // 응답에서 JSON 파싱하여 similarity_score 값 추출
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseEntity.getBody());
            double similarityScore = root.get("similarity_score").asDouble();
            System.out.println("[ 이미지 유사도 : " + similarityScore + " ]");

            return similarityScore;
        } catch (Exception e) {
            e.printStackTrace();
            return -1.0;
        }
    }
}