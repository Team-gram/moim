package ajou.gram.moim.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMoimDto {
    private long userId;
    private int categoryId;
    private String title;
    private String content;
    private String sido;
    private String sigungu;
    private String dong;
    private String isPublish;
    private String isFreeEnter;
    private int maxMember;
}
