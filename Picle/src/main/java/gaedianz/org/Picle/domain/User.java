package gaedianz.org.Picle.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
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
    private String clientKey;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String profileImage;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SocialPlatform socialPlatform;

    private User(String clientKey, String nickname, String profileImage, SocialPlatform socialPlatform) {
        this.clientKey = clientKey;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.socialPlatform = socialPlatform;
    }

    public static User of(String clientKey, String nickname, String profileImage, SocialPlatform socialPlatform) {
        return new User(clientKey, nickname, profileImage, socialPlatform);
    }
}