package gaedianz.org.Picle.controller;

import gaedianz.org.Picle.common.dto.ApiResponse;
import gaedianz.org.Picle.controller.dto.request.RoutineRequestDto;
import gaedianz.org.Picle.controller.dto.response.RoutineResponseDto;
import gaedianz.org.Picle.service.RoutineService;
import gaedianz.org.Picle.exception.Success;
import gaedianz.org.Picle.exception.Error;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/routine/create/{userId}")
    public ApiResponse<List<RoutineResponseDto>> createRoutine(
            @PathVariable("userId") Long userId,
            @RequestBody @Valid final RoutineRequestDto request) {
        List<RoutineResponseDto> routineResponses = routineService.createRoutine(userId, request);

        return success(Success.CREATE_ROUTINE_SUCCESS, routineResponses);
    }

    @DeleteMapping("routine/delete/{userId}/{routineIdentifier}")
    public ApiResponse deleteRoutine(
            @PathVariable Long userId,
            @PathVariable Long routineIdentifier){

        Optional<Long> deletedRoutineId = routineService.deleteRoutine(userId, routineIdentifier);

        if (deletedRoutineId.isPresent()){
            String deletedTodo = "삭제된 routineId : " + deletedRoutineId.get();
            return success(Success.DELETE_ROUTINE_SUCCESS, deletedTodo);
        }

        return error(Error.NOT_FOUND_ROUTINE_EXCEPTION, Error.NOT_FOUND_ROUTINE_EXCEPTION.getMessage());
    }
}
