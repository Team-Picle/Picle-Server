package gaedianz.org.Picle.controller;

import gaedianz.org.Picle.common.dto.ApiResponse;
import gaedianz.org.Picle.controller.dto.request.UserRequestDto;
import gaedianz.org.Picle.controller.dto.response.UserResponseDto;
import gaedianz.org.Picle.domain.User;
import gaedianz.org.Picle.exception.Success;
import gaedianz.org.Picle.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    @Operation(summary = "OAuth2 소셜 로그인")
    @PostMapping("/user/registration")
    public ApiResponse<UserResponseDto> registerUserWithOAuth(@RequestBody @Valid final UserRequestDto request) {
        return ApiResponse.success(Success.SIGNUP_SIGNIN_SUCCESS, userService.registerUserWithOAuth(request));
    }

}
