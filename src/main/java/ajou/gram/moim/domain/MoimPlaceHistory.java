package ajou.gram.moim.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "moim_place_history")
public class MoimPlaceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "address_id")
    private long addressId;
    @Column(name = "place_name")
    private String placeName;
    @Column(name = "category_group_name")
    private String categoryGroupName;
    private String sido;
    private String sigungu;
    private String dong;
    private Date createdAt;

}
