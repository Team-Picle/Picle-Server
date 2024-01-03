package gaedianz.org.Picle.controller;

import gaedianz.org.Picle.common.dto.ApiResponse;
import gaedianz.org.Picle.controller.dto.request.TodoRequestDto;
import gaedianz.org.Picle.controller.dto.response.TodoResponseDto;
import gaedianz.org.Picle.domain.Todo;
import gaedianz.org.Picle.exception.Success;
import gaedianz.org.Picle.exception.Error;
import gaedianz.org.Picle.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/todo/getByDate/{userId}")
    public ApiResponse<List<Todo>> getTodosByDateAndUserId(
            @PathVariable("userId") Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<Todo> todos = todoService.getTodosByDateAndUserId(userId, date);

        return ApiResponse.success(Success.GET_TODOLIST_BY_DATE_SUCCESS, todos);
    }

    @PostMapping("/todo/create/{userId}")
    public ApiResponse<TodoResponseDto> createTodo(
            @PathVariable("userId") Long userId,
            @RequestBody @Valid final TodoRequestDto request){
        TodoResponseDto todoResponse = todoService.createTodo(userId, request);

        return ApiResponse.success(Success.CREATE_TODO_SUCCESS, todoResponse);
    }
}
