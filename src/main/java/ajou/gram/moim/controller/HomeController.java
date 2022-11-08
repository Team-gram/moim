package ajou.gram.moim.controller;

import ajou.gram.moim.domain.User;
import ajou.gram.moim.dto.KakaoDto;
import ajou.gram.moim.repository.UserRepository;
import ajou.gram.moim.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OAuthService oAuthService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/join")
    public User join(@RequestBody User user) {
        user.setId(1234567890);
        user.setName("asd");
        user.setPhone("111");
        user.setGender("M");
        user.setSido("seoul");
        user.setSigungu("gangnam");
        user.setDong("samsung");
        user.setBirthday(new Date());
        user.setRole("USER");
        user.setRegisterDate(new Date());
        user.setLevel((short) 0);
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);
        return user;
    }

    @GetMapping("/kakaologin")
    public long kakaoCallback (@RequestParam String code) {
        String accessToken = oAuthService.getKakaoAccessToken(code);
        KakaoDto kakaoDto = oAuthService.getKakaoUserInfo(accessToken);
        Optional<User> user = userRepository.findById(kakaoDto.getId());
        long id = kakaoDto.getId();
        if (user.isEmpty()) {
            id = 0;
        }
        return id;
    }
}
