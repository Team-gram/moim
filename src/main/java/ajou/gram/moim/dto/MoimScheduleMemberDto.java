package ajou.gram.moim.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoimScheduleMemberDto {
    private long moimId;
    private long moimScheduleId;
    private long userId;
    private String name;
}
