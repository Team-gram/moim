package ajou.gram.moim.domain;

import ajou.gram.moim.domain.idclass.UserCategoryPK;
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
@IdClass(UserCategoryPK.class)
@Entity(name = "user_category")
public class UserCategory {

    @Id @Column(name = "user_id")
    private long userId;
    @Id @Column(name = "category_id")
    private int categoryId;
    @Column(name = "status")
    private short status;
}
