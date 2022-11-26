package ajou.gram.moim.domain;

import ajou.gram.moim.domain.idclass.MoimScheduleMemberPK;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "moim_schedule_member")
@IdClass(MoimScheduleMemberPK.class)
public class MoimScheduleMember {
    @Id @Column(name = "moim_id")
    private long moimId;
    @Id @Column(name = "moim_schedule_id")
    private long moimScheduleId;
    @Id @Column(name = "user_id")
    private long userId;
}
