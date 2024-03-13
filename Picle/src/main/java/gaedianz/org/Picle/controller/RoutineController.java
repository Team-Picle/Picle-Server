package gaedianz.org.Picle.controller;

import gaedianz.org.Picle.common.dto.ApiResponse;
import gaedianz.org.Picle.controller.dto.request.RoutineRequestDto;
import gaedianz.org.Picle.controller.dto.request.UpdateRoutineRequestDto;
import gaedianz.org.Picle.controller.dto.request.VerifyRoutineRequestDto;
import gaedianz.org.Picle.controller.dto.response.FeedResponseDto;
import gaedianz.org.Picle.controller.dto.response.PreviewResponseDto;
import gaedianz.org.Picle.controller.dto.response.RoutineResponseDto;
import gaedianz.org.Picle.controller.dto.response.VerifyRoutineResponseDto;
import gaedianz.org.Picle.service.RoutineService;
import gaedianz.org.Picle.exception.Success;
import gaedianz.org.Picle.exception.Error;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static gaedianz.org.Picle.common.dto.ApiResponse.error;
import static gaedianz.org.Picle.common.dto.ApiResponse.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RoutineController {

    @Autowired
    private RoutineService routineService;

    @GetMapping("/routine/getPreviews/{userId}")
    public ApiResponse<List<PreviewResponseDto>> getPreviews(
            @PathVariable Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<PreviewResponseDto> previews = routineService.getPreviews(userId, date);
        return success(Success.GET_PREVIEW_BY_DATE_SUCCESS, previews);
    }

    @GetMapping("/routine/getByDate/{userId}")
    public ApiResponse<List<RoutineResponseDto>> getByDate(
            @PathVariable("userId") Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<RoutineResponseDto> routines = routineService.getByDate(userId, date);
        return ApiResponse.success(Success.GET_ROUTINELIST_BY_DATE_SUCCESS, routines);
    }

    @GetMapping("/routine/getMyFeeds/{userId}")
    public ApiResponse<List<FeedResponseDto>> getMyFeeds(
            @PathVariable Long userId
    ) {
        List<FeedResponseDto> feeds = routineService.getMyFeeds(userId);
        return success(Success.GET_MY_FEEDS_SUCCESS, feeds);
    }

    @GetMapping("/routine/getAllFeeds")
    public ApiResponse<List<FeedResponseDto>> getAllFeeds() {
        List<FeedResponseDto> feeds = routineService.getAllFeeds();
        return success(Success.GET_ALL_FEEDS_SUCCESS, feeds);
    }

    @PostMapping("/routine/createPreview/{userId}")
    public ApiResponse<RoutineResponseDto> createPreview(
            @PathVariable("userId") Long userId,
            @RequestBody @Valid final RoutineRequestDto request) {
        RoutineResponseDto routineResponse = routineService.createPreview(userId, request);

        return success(Success.CREATE_PREVIEW_SUCCESS, routineResponse);
    }

    @PostMapping("/routine/createRoutine/{userId}/{routineId}")
    public ApiResponse<RoutineResponseDto> createRoutine(
            @PathVariable("userId") Long userId,
            @PathVariable("routineId") Long routineId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        RoutineResponseDto routineResponse = routineService.createRoutine(userId, routineId, date);

        return success(Success.CREATE_ROUTINE_SUCCESS, routineResponse);
    }

    @PatchMapping("/routine/update/{userId}/{routineId}")
    public ApiResponse<RoutineResponseDto> updatePreview(
            @PathVariable Long userId,
            @PathVariable Long routineId,
            @RequestBody @Valid UpdateRoutineRequestDto request) {
        RoutineResponseDto updatePreviewResponse = routineService.updatePreview(userId, routineId, request);
        return success(Success.UPDATE_ROUTINE_SUCCESS, updatePreviewResponse);
    }

    @PatchMapping("/routine/verify/{userId}/{routineId}")
    public ApiResponse<VerifyRoutineResponseDto> verifyRoutine(
            @PathVariable Long userId,
            @PathVariable Long routineId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestBody @Valid VerifyRoutineRequestDto request) {
        VerifyRoutineResponseDto verifyRoutineResponseDto = routineService.verifyRoutine(userId, routineId, date, request);
        return success(Success.VERIFY_ROUTINE_SUCCESS, verifyRoutineResponseDto);
    }

    @DeleteMapping("routine/delete/{userId}/{routineId}")
    public ApiResponse deleteRoutine(
            @PathVariable Long userId,
            @PathVariable Long routineId){
        Optional<Long> deletedRoutineId = routineService.deleteRoutine(userId, routineId);

        if (deletedRoutineId.isPresent()){
            String deletedRoutine = "삭제된 routineId : " + deletedRoutineId.get();
            return success(Success.DELETE_ROUTINE_SUCCESS, deletedRoutine);
        }

        return error(Error.NOT_FOUND_ROUTINE_EXCEPTION, Error.NOT_FOUND_ROUTINE_EXCEPTION.getMessage());
    }

    @DeleteMapping("routine/finish/{userId}/{routineId}")
    public ApiResponse finishRoutine(
            @PathVariable Long userId,
            @PathVariable Long routineId){
        Optional<Long> deletedRoutineId = routineService.deleteRoutine(userId, routineId);

        if (deletedRoutineId.isPresent()){
            String deletedRoutine = "종료된 routineId : " + deletedRoutineId.get();
            return success(Success.FINISH_ROUTINE_SUCCESS, deletedRoutine);
        }

        return error(Error.NOT_FOUND_ROUTINE_EXCEPTION, Error.NOT_FOUND_ROUTINE_EXCEPTION.getMessage());
    }
}