package gaedianz.org.Picle.controller;

import gaedianz.org.Picle.common.dto.ApiResponse;
import gaedianz.org.Picle.controller.dto.request.TodoRequestDto;
import gaedianz.org.Picle.controller.dto.request.UpdateTodoRequestDto;
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
import java.util.Optional;

import static gaedianz.org.Picle.common.dto.ApiResponse.error;
import static gaedianz.org.Picle.common.dto.ApiResponse.success;

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

        return success(Success.GET_TODOLIST_BY_DATE_SUCCESS, todos);
    }

    @GetMapping("/todo/getCompletedByDate/{userId}")
    public ApiResponse<List<Todo>> getCompletedTodosByDateAndUserId(
            @PathVariable("userId") Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<Todo> completedTodos = todoService.getCompletedTodos(userId, date);

        return ApiResponse.success(Success.GET_COMPLETED_TODOLIST_BY_DATE_SUCCESS, completedTodos);
    }

    @PostMapping("/todo/create/{userId}")
    public ApiResponse<TodoResponseDto> createTodo(
            @PathVariable("userId") Long userId,
            @RequestBody @Valid final TodoRequestDto request){
        TodoResponseDto todoResponse = todoService.createTodo(userId, request);

        return success(Success.CREATE_TODO_SUCCESS, todoResponse);
    }

    @PatchMapping("todo/update")
    public ApiResponse<TodoResponseDto> updateTodo(
            @RequestParam Long userId,
            @RequestParam Long todoId,
            @RequestBody final UpdateTodoRequestDto request){
        TodoResponseDto todoResponse = todoService.updateTodo(userId, todoId, request);

        return success(Success.UPDATE_TODO_SUCCESS, todoResponse);
    }

    @DeleteMapping("todo/delete")
    public ApiResponse deleteTodo(
            @RequestParam Long userId,
            @RequestParam Long todoId){
        Optional<Long> deletedTodoId = todoService.deleteTodo(userId, todoId);

        if (deletedTodoId.isPresent()){
            String deletedTodo = "삭제된 todoId : " + deletedTodoId.get();
            return success(Success.DELETE_TODO_SUCCESS, deletedTodo);
        }

        return error(Error.NOT_FOUND_TODO_EXCEPTION, Error.NOT_FOUND_TODO_EXCEPTION.getMessage());
    }

}
