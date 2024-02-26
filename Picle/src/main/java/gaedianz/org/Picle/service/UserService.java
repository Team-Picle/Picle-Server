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

        // 유저 존재 여부 검증
        if (userRepository.existsByClientKey(request.getClientKey())) {
            throw new ConflictException(Error.ALREADY_EXIST_USER_EXCEPTION, Error.ALREADY_EXIST_USER_EXCEPTION.getMessage());
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