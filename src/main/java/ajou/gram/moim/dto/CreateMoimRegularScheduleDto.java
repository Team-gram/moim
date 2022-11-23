package ajou.gram.moim.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMoimRegularScheduleDto {
    private long moimId;
    private short day;
    private String startTime;
    private String endTime;
    private String scheduleName;
    private String scheduleDetail;
}