package ajou.gram.moim.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoimMemberScheduleDto {

    private long moimId;
    private long userId;
    private String name;
    private short day;
    private String startTime;
    private String endTime;
    private String scheduleName;
    private String scheduleDetail;
}
