package gaedianz.org.Picle.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Routine extends AuditingTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id")
    private Long id;

    private Long routineIdentifier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private User user;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String registrationImgUrl;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> verifiedImgUrl = new ArrayList<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column(nullable = false)
    private LocalDate date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private LocalTime time;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column(nullable = false)
    private LocalDate startRepeatDate;

    @ElementCollection
    @CollectionTable(name = "routine_repeat_days", joinColumns = @JoinColumn(name = "routine_id"))
    @Column(name = "repeat_day", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> repeatDays;

    @Column(nullable = false)
    private Double destinationLongitude;

    @Column(nullable = false)
    private Double destinationLatitude;

    private Double currentLongitude;

    private Double currentLatitude;

    @Column(nullable = false)
    private Boolean isCompleted;

    private Routine(User user, String content, String registrationImgUrl, LocalDate date, LocalTime time,
                    LocalDate startRepeatDate, Set<DayOfWeek> repeatDays,
                    Double destinationLongitude, Double destinationLatitude, Boolean isCompleted) {
        this.user = user;
        this.content = content;
        this.registrationImgUrl = registrationImgUrl;
        this.date = date;
        this.time = time;
        this.startRepeatDate = startRepeatDate;
        this.repeatDays = repeatDays;
        this.destinationLongitude = destinationLongitude;
        this.destinationLatitude = destinationLatitude;
        this.isCompleted = isCompleted;
    }

    public static Routine newInstance(User user, String content, String registrationImgUrl, LocalDate date, LocalTime time,
                                      LocalDate startRepeatDate, Set<DayOfWeek> repeatDays,
                                      Double destinationLongitude, Double destinationLatitude, Boolean isCompleted) {
        return new Routine(user, content, registrationImgUrl, date, time, startRepeatDate, repeatDays, destinationLongitude, destinationLatitude, isCompleted);
    }

    public void addImage(Image image) {
        verifiedImgUrl.add(image);
        image.setRoutine(this);
    }
}
