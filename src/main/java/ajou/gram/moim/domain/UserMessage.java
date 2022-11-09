package ajou.gram.moim.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_message")
public class UserMessage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "from_id")
    private long fromId;
    @Column(name = "to_id")
    private long toId;
    @Column(name = "moim_id")
    private long moimId;
    private String type;
    private String message;
    private short status;
}
