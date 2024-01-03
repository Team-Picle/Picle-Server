package gaedianz.org.Picle.service;

import gaedianz.org.Picle.controller.dto.request.TodoRequestDto;
import gaedianz.org.Picle.controller.dto.response.TodoResponseDto;
import gaedianz.org.Picle.domain.Todo;
import gaedianz.org.Picle.domain.User;
import gaedianz.org.Picle.exception.Error;
import gaedianz.org.Picle.exception.model.NotFoundException;
import gaedianz.org.Picle.infrastructure.TodoRepository;
import gaedianz.org.Picle.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public TodoResponseDto createTodo(Long userId, TodoRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));
        Todo todo = Todo.newInstance(user, request.getContent(), request.getDate(), request.isCompleted());
        todoRepository.save(todo);

        return TodoResponseDto.of(todo.getId(), userId, todo.getContent(), todo.getDate(), todo.isCompleted());
    }
}