package ajou.gram.moim.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaceHistoryDto {

    private long addressId;
    private String placeName;
    private String categoryGroupName;
    private String sido;
    private String sigungu;
    private String dong;
}