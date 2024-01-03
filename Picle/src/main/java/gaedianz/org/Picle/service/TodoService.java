package gaedianz.org.Picle.service;

import gaedianz.org.Picle.common.dto.ApiResponse;
import gaedianz.org.Picle.controller.dto.request.TodoRequestDto;
import gaedianz.org.Picle.controller.dto.request.UpdateTodoRequestDto;
import gaedianz.org.Picle.controller.dto.response.TodoResponseDto;
import gaedianz.org.Picle.domain.Todo;
import gaedianz.org.Picle.domain.User;
import gaedianz.org.Picle.exception.Error;
import gaedianz.org.Picle.exception.model.NotFoundException;
import gaedianz.org.Picle.infrastructure.TodoRepository;
import gaedianz.org.Picle.infrastructure.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final UserRepository userRepository;

    private final TodoRepository todoRepository;

    public List<Todo> getTodosByDateAndUserId(Long userId, LocalDate date) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));
        return todoRepository.findByUserIdAndDate(userId, date);
    }

    public List<Todo> getCompletedTodos(Long userId, LocalDate date) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));
        return todoRepository.findCompletedTodos(userId, date);
    }

    @Transactional
    public TodoResponseDto createTodo(Long userId, TodoRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));
        Todo todo = Todo.newInstance(user, request.getContent(), request.getDate(), request.isCompleted());
        todoRepository.save(todo);

        return TodoResponseDto.of(todo.getId(), userId, todo.getContent(), todo.getDate(), todo.getIsCompleted());
    }

    @Transactional
    public TodoResponseDto updateTodo(Long userId, Long todoId, UpdateTodoRequestDto request) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));
        Todo todo = todoRepository.findByUserIdAndTodoId(userId, todoId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_TODO_EXCEPTION, Error.NOT_FOUND_TODO_EXCEPTION.getMessage()));

        if (request.getContent() != null) {
            todo.setContent(request.getContent());
        }
        if (request.getDate() != null) {
            todo.setDate(request.getDate());
        }
        if (request.getIsCompleted() != null && !todo.getDate().isAfter(LocalDate.now())) {
            todo.setIsCompleted(request.getIsCompleted());
        }

        todoRepository.save(todo);

        return TodoResponseDto.of(todo.getId(), userId, todo.getContent(), todo.getDate(), todo.getIsCompleted());
    }

    @Transactional
    public Optional<Long> deleteTodo(Long userId, Long todoId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));
        Todo todo = todoRepository.findByUserIdAndTodoId(userId, todoId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_TODO_EXCEPTION, Error.NOT_FOUND_TODO_EXCEPTION.getMessage()));

        Long deletedTodoId = todo.getId();

        todoRepository.deleteById(todo.getId());

        return Optional.of(deletedTodoId);
    }
}