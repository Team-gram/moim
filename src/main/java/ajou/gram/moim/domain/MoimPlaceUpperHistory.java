package ajou.gram.moim.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "moim_place_upper_history")
public class MoimPlaceUpperHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "place_upper_id")
    private long placeUpperId;
}
