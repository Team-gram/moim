package ajou.gram.moim.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecommendMoimDto {

    private int members;
    private long moimId;
    private int categoryParentId;
    private int categoryId;
    private String title;
    private String content;
    private String sido;
    private String sigungu;
    private String dong;
    private String isFreeEnter;
    private int maxMember;
    private Date createDate;
    private int moimLevel;
}
