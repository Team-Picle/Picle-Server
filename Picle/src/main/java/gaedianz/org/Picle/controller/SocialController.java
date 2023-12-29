package gaedianz.org.Picle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import gaedianz.org.Picle.common.dto.ApiResponse;
import gaedianz.org.Picle.controller.dto.request.SocialLoginRequestDto;
import gaedianz.org.Picle.exception.Success;
import gaedianz.org.Picle.service.SocialService;
import gaedianz.org.Picle.service.SocialServiceProvider;
import gaedianz.org.Picle.service.dto.request.SocialLoginRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/social")
public class SocialController {

    private final SocialServiceProvider socialServiceProvider;

//    @PostMapping("/login")
//    public ApiResponse<Long> login(@RequestHeader("code") String code, @RequestBody SocialLoginRequestDto request) {
//        SocialService socialService = socialServiceProvider.getSocialService(request.getSocialPlatform());
//        return ApiResponse.success(Success.LOGIN_SUCCESS, socialService.login(SocialLoginRequest.of(code)));
//    }

    @PostMapping("/login")
    public ApiResponse<Long> login(@RequestParam("code") String code, @RequestBody SocialLoginRequestDto request) {
        SocialService socialService = socialServiceProvider.getSocialService(request.getSocialPlatform());
        return ApiResponse.success(Success.LOGIN_SUCCESS, socialService.login(SocialLoginRequest.of(code)));
    }
}
