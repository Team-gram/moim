package ajou.gram.moim.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "moim_regular_schedule")
public class MoimRegularSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "moim_id")
    private long moimId;
    private short day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String scheduleName;
    private String scheduleDetail;
}
