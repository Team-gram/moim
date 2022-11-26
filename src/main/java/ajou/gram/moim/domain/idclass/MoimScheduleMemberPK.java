package ajou.gram.moim.domain.idclass;

import lombok.Data;

import java.io.Serializable;

@Data
public class MoimScheduleMemberPK implements Serializable {
    private long moimId;
    private long moimScheduleId;
    private long userId;
}
