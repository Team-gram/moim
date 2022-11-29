package ajou.gram.moim.domain;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "moim")
public class Moim {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_id")
    private long userId;
    @Column(name = "category_id")
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
    private short moimLevel;
    private String thumbnail;
}
