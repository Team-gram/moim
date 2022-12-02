package ajou.gram.moim.dto;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrintChatDto {

    private long id;
    private long moimId;
    private long userId;
    private String name;
    private String content;
    private int decoration;
    private Date createdAt;
}
