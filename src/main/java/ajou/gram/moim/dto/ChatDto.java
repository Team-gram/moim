package ajou.gram.moim.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {

    private long moimId;
    private long userId;
    private String content;
}
