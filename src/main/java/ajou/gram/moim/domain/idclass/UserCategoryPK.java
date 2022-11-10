package ajou.gram.moim.domain.idclass;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserCategoryPK implements Serializable {
    private long userId;
    private int categoryId;
}
