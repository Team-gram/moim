package ajou.gram.moim.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoimUpperDto {
    private long moimId;
    private int period;
    private int money;
}
