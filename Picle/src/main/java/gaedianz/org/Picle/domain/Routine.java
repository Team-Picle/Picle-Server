package gaedianz.org.Picle.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Routine extends AuditingTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private User user;

    @Column(nullable = false)
    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private boolean isCompleted;

    private Routine(User user, String title, LocalDateTime dateTime, Boolean isCompleted) {
        this.user = user;
        this.title = title;
        this.dateTime = dateTime;
        this.isCompleted = isCompleted;
    }

    public static Routine newInstance(User user, String title, LocalDateTime dateTime, Boolean isCompleted) {
        return new Routine(user, title, dateTime, isCompleted);
    }
}
