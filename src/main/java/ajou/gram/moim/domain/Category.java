package ajou.gram.moim.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "category")
public class Category {
    @Id @Column(name = "category_id")
    private int categoryId;
    @Column(name = "category_parent_id")
    private int categoryParentId;
    @Column(name = "category_name")
    private String categoryName;
}
