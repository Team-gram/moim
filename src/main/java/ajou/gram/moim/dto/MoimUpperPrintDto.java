package ajou.gram.moim.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoimUpperPrintDto {
    private int count;
    private long id;
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
    private Date createDate;
    private int moimLevel;
    private String thumbnail;
}
