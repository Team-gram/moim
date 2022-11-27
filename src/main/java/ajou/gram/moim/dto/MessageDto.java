package ajou.gram.moim.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {
    private long fromId;
    private long toId;
    private String type;
}
