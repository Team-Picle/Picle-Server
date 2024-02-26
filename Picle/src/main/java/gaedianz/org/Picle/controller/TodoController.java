package gaedianz.org.Picle.controller;

import gaedianz.org.Picle.common.dto.ApiResponse;
import gaedianz.org.Picle.controller.dto.request.TodoRequestDto;
import gaedianz.org.Picle.controller.dto.request.UpdateTodoRequestDto;
import gaedianz.org.Picle.controller.dto.response.TodoResponseDto;
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
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/todo/getByDate/{userId}")
    public ApiResponse<List<TodoResponseDto>> getByDate(
            @PathVariable("userId") Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<TodoResponseDto> todos = todoService.getByDate(userId, date);
        return ApiResponse.success(Success.GET_TODOLIST_BY_DATE_SUCCESS, todos);
    }

    @PostMapping("/todo/create/{userId}")
    public ApiResponse<TodoResponseDto> createTodo(
            @PathVariable("userId") Long userId,
            @RequestBody @Valid final TodoRequestDto request){
        TodoResponseDto todoResponse = todoService.createTodo(userId, request);

        return ApiResponse.success(Success.CREATE_TODO_SUCCESS, todoResponse);
    }

    @PatchMapping("todo/update/{userId}/{todoId}")
    public ApiResponse<TodoResponseDto> updateTodo(
            @PathVariable Long userId,
            @PathVariable Long todoId,
            @RequestBody final UpdateTodoRequestDto request){
        TodoResponseDto todoResponse = todoService.updateTodo(userId, todoId, request);

        return ApiResponse.success(Success.UPDATE_TODO_SUCCESS, todoResponse);
    }

    @DeleteMapping("todo/delete/{userId}/{todoId}")
    public ApiResponse deleteTodo(
            @PathVariable Long userId,
            @PathVariable Long todoId){
        Optional<Long> deletedTodoId = todoService.deleteTodo(userId, todoId);

        if (deletedTodoId.isPresent()){
            String deletedTodo = "삭제된 todoId : " + deletedTodoId.get();
            return ApiResponse.success(Success.DELETE_TODO_SUCCESS, deletedTodo);
        }

        return ApiResponse.error(Error.NOT_FOUND_TODO_EXCEPTION, Error.NOT_FOUND_TODO_EXCEPTION.getMessage());
    }
}