package gaedianz.org.Picle.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo extends AuditingTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private User user;

    @Column(name = "content", nullable = false)
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "is_completed")
    private Boolean isCompleted;

    private Todo(User user, String content, LocalDate date, Boolean isCompleted) {
        this.user = user;
        this.content = content;
        this.date = date;
        this.isCompleted = isCompleted;
    }

    public static Todo newInstance(User user, String content, LocalDate date, Boolean isCompleted) {
        return new Todo(user, content, date, isCompleted);
    }
}