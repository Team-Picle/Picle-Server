package gaedianz.org.Picle.service;

import gaedianz.org.Picle.controller.dto.request.UserRequestDto;
import gaedianz.org.Picle.controller.dto.response.UserResponseDto;
import gaedianz.org.Picle.domain.User;
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

        User newUser = User.of(
                request.getNickname(),
                request.getProfileImage(),
                request.getSocialPlatform(),
                request.getAccessToken()
        );

        userRepository.save(newUser);

        return UserResponseDto.of(newUser.getId(), newUser.getNickname(), newUser.getProfileImage());
    }

}