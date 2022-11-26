package ajou.gram.moim.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoimScheduleReferenceDto {
    private long moimId;
    private long moimScheduleId;
    private long userId;
    private String name;
    private String status;
    private String userName;
}
