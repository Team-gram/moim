package ajou.gram.moim.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JoinDto {
    private long id;
    private String name;
    private String profileImage;
    private String sido;
    private String sigungu;
    private String dong;
    private String gender;
    private String birthday;
    private String detail;
    private String isPublish;
    private List<Integer> categories;
}
