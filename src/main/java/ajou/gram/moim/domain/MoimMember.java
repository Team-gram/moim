package ajou.gram.moim.domain;

import ajou.gram.moim.domain.idclass.MoimMemberPK;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "moim_member")
@IdClass(MoimMemberPK.class)
public class MoimMember {

    @Id @Column(name = "moim_id")
    private long moimId;
    @Id @Column(name = "user_id")
    private long userId;
    private Date registerDate;
    private short level;
}
