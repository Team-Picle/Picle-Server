package gaedianz.org.Picle.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Routine routine;

    @Column(nullable = false)
    private String imageUrl;

    private Image(Routine routine, String imageUrl) {
        this.routine = routine;
        this.imageUrl = imageUrl;
    }

    public static Image newInstance(Routine routine, String imageUrl) {
        Image image = new Image(routine, imageUrl);
        routine.addImage(image);
        return image;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }
}
