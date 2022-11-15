package ajou.gram.moim.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateRegularScheduleDto {
    private long userId;
    private short day;
    private String startTime;
    private String endTime;
    private String title;
    private String detail;
}
