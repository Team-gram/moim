package ajou.gram.moim.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoimPlaceUpperDto {
    private long userId;
    private long placeId;   // 사업자 등록번호
    private String placeName;
    private int categoryId;
    private String page;    // 업소를 소개하는 웹페이지
    private MultipartFile crn;     // 사업자 등록증 파일
    private String sido;
    private String sigungu;
    private String dong;
    private int period;
    private int money;
}
