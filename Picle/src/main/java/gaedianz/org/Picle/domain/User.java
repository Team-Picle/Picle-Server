package gaedianz.org.Picle.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String profileImage;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SocialPlatform socialPlatform;

    @Column(nullable = false)
    private String accessToken;

    @Column(nullable = false)
    private String refreshToken;

    private User(String nickname, String profileImage, SocialPlatform socialPlatform, String accessToken, String refreshToken) {
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.socialPlatform = socialPlatform;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static User of(String nickname, String profileImage, SocialPlatform socialPlatform, String accessToken, String refreshToken) {
        return new User(nickname, profileImage, socialPlatform, accessToken, refreshToken);
    }
}
