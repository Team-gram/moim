package ajou.gram.moim.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_irregular_schedule")
public class UserIrregularSchedule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_id")
    private long userId;
    private Date date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String title;
    private String detail;
}
