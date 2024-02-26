package gaedianz.org.Picle.service;

import gaedianz.org.Picle.controller.dto.request.TodoRequestDto;
import gaedianz.org.Picle.controller.dto.request.UpdateTodoRequestDto;
import gaedianz.org.Picle.controller.dto.response.FeedResponseDto;
import gaedianz.org.Picle.controller.dto.response.TodoResponseDto;
import gaedianz.org.Picle.domain.Routine;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public List<TodoResponseDto> getByDate(Long userId, LocalDate date) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));
        List<Todo> todos = todoRepository.findByUserIdAndDate(userId, date);

        return todos.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public TodoResponseDto createTodo(Long userId, TodoRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));
        Todo todo = Todo.newInstance(user, request.getContent(), request.getDate(), false);
        todoRepository.save(todo);

        return convertToResponseDto(todo);
    }

    @Transactional
    public TodoResponseDto updateTodo(Long userId, Long todoId, UpdateTodoRequestDto request) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));
        Todo todo = todoRepository.findByIdAndUserId(todoId, userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_TODO_EXCEPTION, Error.NOT_FOUND_TODO_EXCEPTION.getMessage()));

        if (request.getContent() != null) {
            todo.setContent(request.getContent());
        }
        if (request.getDate() != null) {
            todo.setDate(request.getDate());
        }
        if (request.getIsCompleted() != null) {
            todo.setIsCompleted(request.getIsCompleted());
        }

        todoRepository.save(todo);

        return convertToResponseDto(todo);
    }

    @Transactional
    public Optional<Long> deleteTodo(Long userId, Long todoId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));
        Todo todo = todoRepository.findByIdAndUserId(todoId, userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_TODO_EXCEPTION, Error.NOT_FOUND_TODO_EXCEPTION.getMessage()));

        todoRepository.delete(todo);

        return Optional.of(todoId);
    }

    private TodoResponseDto convertToResponseDto(Todo todo) {
        return TodoResponseDto.of(
                todo.getId(),
                todo.getUser().getId(),
                todo.getContent(),
                todo.getDate(),
                todo.getIsCompleted()
        );
    }
}