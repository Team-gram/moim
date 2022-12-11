package ajou.gram.moim.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_regular_schedule")
public class UserRegularSchedule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_id")
    private long userId;
    private short day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String title;
    private String detail;
    private String isPublish;
}
