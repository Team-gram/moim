package ajou.gram.moim.controller;

import ajou.gram.moim.domain.User;
import ajou.gram.moim.dto.JoinDto;
import ajou.gram.moim.dto.KakaoDto;
import ajou.gram.moim.dto.LoginDto;
import ajou.gram.moim.repository.UserRepository;
import ajou.gram.moim.service.OAuthService;
import ajou.gram.moim.service.UserService;
import org.hibernate.mapping.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.text.ParseException;
import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private OAuthService oAuthService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/join")
    public JoinDto join(@RequestBody JoinDto joinDto) throws ParseException {
        userService.addUser(joinDto);
        return joinDto;
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginDto loginDto) {

    }

    @GetMapping("/kakaologin")
    public long kakaoCallback (@RequestParam String code) {
        String accessToken = oAuthService.getKakaoAccessToken(code);
        KakaoDto kakaoDto = oAuthService.getKakaoUserInfo(accessToken);
        Optional<User> user = userService.validateId(kakaoDto.getId());
        long id = kakaoDto.getId();
        if (user.isEmpty()) {
            id = 0;
        }
        return id;
    }
}
