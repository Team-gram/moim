package ajou.gram.moim.domain;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class User {

    public User(long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "name", length = 10)
    private String name;
    @Column(name = "profile_image")
    private String profileImage;
    @Column(name = "phone", length = 11)
    private String phone;
    @Column(name = "sido", length = 10)
    private String sido;
    @Column(name = "sigungu", length = 10)
    private String sigungu;
    @Column(name = "dong", length = 10)
    private String dong;
    @Column(name = "gender", length = 1)
    private String gender;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "detail")
    private String detail;
    @Column(name = "isPublish")
    private String isPublish;
    @Column(name = "registerDate")
    private Date registerDate;
    @Column(name = "lastLoginDate")
    private Date lastLoginDate;
    @Column(name = "retireDate")
    private Date retireDate;
    @Column(name = "level")
    private short level;
    @Column(name = "role")
    private String role;
}