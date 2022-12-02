package ajou.gram.moim.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoimPlaceDto {
    private long moimId;
    private long scheduleId;
    private long addressId;
    private String placeName;
    private String sido;
    private String sigungu;
    private String dong;
}
