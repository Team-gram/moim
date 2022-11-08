package ajou.gram.moim.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KakaoDto {

    private long id;
    private String nickName;
    private String profileImage;
}
