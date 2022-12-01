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
@Entity(name = "moim_upper_history")
public class MoimUpperHistory {
    @Id
    private long id;
    @Column(name = "moim_id")
    private long moimId;
}
