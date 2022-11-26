package ajou.gram.moim.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JoinMoimScheduleDto {
    private long moimId;
    private long moimScheduleId;
    private long userId;
}
