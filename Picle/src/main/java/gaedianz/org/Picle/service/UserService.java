package gaedianz.org.Picle.service;

import gaedianz.org.Picle.controller.dto.request.UserRequestDto;
import gaedianz.org.Picle.controller.dto.response.UserResponseDto;
import gaedianz.org.Picle.domain.User;
import gaedianz.org.Picle.exception.Error;
import gaedianz.org.Picle.exception.model.ConflictException;
import gaedianz.org.Picle.infrastructure.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto registerUserWithOAuth(UserRequestDto request) {

        // 유저가 존재하면 유저 정보 리턴
        if (userRepository.existsByClientKey(request.getClientKey())) {
            User user = userRepository.findByClientKey(request.getClientKey());
            return UserResponseDto.of(user.getClientKey(), user.getId(), user.getNickname(), user.getProfileImage());
        }
        User user = User.of(
                request.getClientKey(),
                request.getNickname(),
                request.getProfileImage(),
                request.getSocialPlatform()
        );
        userRepository.save(user);

        return UserResponseDto.of(user.getClientKey(), user.getId(), user.getNickname(), user.getProfileImage());
    }

}