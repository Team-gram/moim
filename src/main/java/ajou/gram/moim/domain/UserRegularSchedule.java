package ajou.gram.moim.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_regular_schedule")
public class UserRegularSchedule {
    @Id
    private long id;
    @Column(name = "user_id")
    private long userId;
    private short day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String title;
    private String detail;
}
