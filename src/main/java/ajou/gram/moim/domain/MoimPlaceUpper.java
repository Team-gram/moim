package ajou.gram.moim.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "moim_place_upper")
public class MoimPlaceUpper {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "place_id")
    private long placeId;
    @Column(name = "place_name")
    private String placeName;
    @Column(name = "category_id")
    private int categoryId;
    private String page;    // 업소를 소개하는 웹페이지
    private String crn;     // 사업자 등록증 파일
    private String sido;
    private String sigungu;
    private String dong;
    private int period;
    private int money;
    private LocalDateTime createDate;
    private LocalDateTime recStartDate;
    private LocalDateTime recEndDate;
    private String status;
}
