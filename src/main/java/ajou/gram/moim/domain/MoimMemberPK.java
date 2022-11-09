package ajou.gram.moim.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class MoimMemberPK implements Serializable {
    private long moimId;
    private long userId;
}
