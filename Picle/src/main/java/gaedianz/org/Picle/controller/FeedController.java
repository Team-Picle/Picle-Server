package gaedianz.org.Picle.controller;

import gaedianz.org.Picle.common.dto.ApiResponse;
import gaedianz.org.Picle.controller.dto.response.ExploreFeedResponseDto;
import gaedianz.org.Picle.controller.dto.response.MyFeedResponseDto;
import gaedianz.org.Picle.service.RoutineService;
import gaedianz.org.Picle.exception.Success;
import gaedianz.org.Picle.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FeedController {

    private final RoutineService routineService;
    private final LikeService likeService;

    @GetMapping("/routine/getMyFeeds/{userId}")
    public ApiResponse<List<MyFeedResponseDto>> getMyFeed(@PathVariable Long userId) {
        List<MyFeedResponseDto> responseDtos = routineService.getMyFeeds(userId);

        return ApiResponse.success(
                Success.GET_MY_FEED_SUCCESS,
                responseDtos
        );
    }

    @GetMapping("/routine/getAllFeeds/{userId}")
    public ApiResponse<List<ExploreFeedResponseDto>> getExploreFeed(@PathVariable Long userId) {
        List<ExploreFeedResponseDto> responseDtos = routineService.getAllFeeds(userId);

        return ApiResponse.success(
                Success.GET_EXPLORE_FEED_SUCCESS,
                responseDtos
        );
    }

    @PostMapping("/{routineId}/like/{userId}")
    public ApiResponse<Void> likeRoutine(
            @PathVariable Long routineId,
            @PathVariable Long userId
    ) {
        likeService.likeRoutine(userId, routineId);
        return ApiResponse.success(
                Success.LIKE_ROUTINE_SUCCESS
        );
    }

    @DeleteMapping("/{routineId}/like/{userId}")
    public ApiResponse<Void> unlikeRoutine(
            @PathVariable Long routineId,
            @PathVariable Long userId
    ) {
        likeService.unlikeRoutine(userId, routineId);
        return ApiResponse.success(
                Success.UNLIKE_ROUTINE_SUCCESS
        );
    }
}
