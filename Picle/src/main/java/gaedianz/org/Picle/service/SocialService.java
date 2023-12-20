package gaedianz.org.Picle.service;

import gaedianz.org.Picle.service.dto.request.SocialLoginRequest;

public abstract class SocialService {
    public abstract Long login(SocialLoginRequest request);

    public void logout(Long userId) {
        // 어쩌고 저쩌고 로직 -> 이게 공통되니까 login만 각자 구현하면 된다!
    }
}
