package gaedianz.org.Picle.controller.dto.response;

import gaedianz.org.Picle.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoResponseDto {
    private Long todoId;
    private Long userId;
    private String content;
    private LocalDate date;
    private boolean isCompleted;

    public static TodoResponseDto of(Long todoId, Long userId, String content, LocalDate date, boolean isCompleted) {
        return new TodoResponseDto(todoId, userId, content, date, isCompleted);
    }
}
