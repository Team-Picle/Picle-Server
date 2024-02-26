package gaedianz.org.Picle.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoResponseDto {
    private Long id;
    private Long userId;
    private String content;
    private LocalDate date;
    private Boolean isCompleted;

    public static TodoResponseDto of(Long id, Long userId, String content, LocalDate date, Boolean isCompleted) {
        return new TodoResponseDto(id, userId, content, date, isCompleted);
    }
}
