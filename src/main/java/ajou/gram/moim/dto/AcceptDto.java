package ajou.gram.moim.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AcceptDto {
    private long messageId;
    private long moimId;
    private long userId;
}
