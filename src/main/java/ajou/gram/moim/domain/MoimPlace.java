package ajou.gram.moim.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "moim_place")
public class MoimPlace {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "moim_id")
    private long moimId;
    @Column(name = "schedule_id")
    private long scheduleId;
    @Column(name = "address_id")
    private long addressId;
    @Column(name = "place_name")
    private String placeName;
    private String sido;
    private String sigungu;
    private String dong;
}
