package ajou.gram.moim.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "moim_chat")
public class MoimChat {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "moim_id")
    private long moimId;
    @Column(name = "user_id")
    private long userId;
    private String content;
    private int decoration;
    private Date createdAt;
}
