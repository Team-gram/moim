package ajou.gram.moim.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "moim_upper")
public class MoimUpper {

    @Id @Column(name = "moim_id")
    private long moimId;
    private int period;
    private int money;
    private LocalDateTime recStartDate;
    private LocalDateTime recEndDate;
}
