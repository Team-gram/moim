package ajou.gram.moim.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "moim_schedule_reference")
public class MoimScheduleReference {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "moim_id")
    private long moimId;
    @Column(name = "moim_schedule_id")
    private long moimScheduleId;
    @Column(name = "user_id")
    private long userId;
    private String name;
    private String status;
}
