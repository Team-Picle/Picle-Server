package gaedianz.org.Picle.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "routine_like",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_routine_user",
                        columnNames = {"routine_id", "user_id"}
                )
        }
)
public class RoutineLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT)
    )
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "routine_id",
            nullable = false,
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT)
    )
    private Routine routine;

    private RoutineLike(User user, Routine routine) {
        this.user = user;
        this.routine = routine;
    }

    public static RoutineLike of(User user, Routine routine) {
        return new RoutineLike(user, routine);
    }
}
