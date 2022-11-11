package ajou.gram.moim.controller;

import ajou.gram.moim.domain.User;
import ajou.gram.moim.dto.JoinDto;
import ajou.gram.moim.dto.KakaoDto;
import ajou.gram.moim.dto.LoginDto;
import ajou.gram.moim.service.OAuthService;
import ajou.gram.moim.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final OAuthService oAuthService;

    @PostMapping("/join")
    public JoinDto join(@RequestBody JoinDto joinDto) throws ParseException {
        userService.addUser(joinDto);
        return joinDto;
    }

    @GetMapping("/login")
    public String login() {
        return "https://kauth.kakao.com/oauth/authorize?client_id=27769c331d08ceb2033e090a83e1e212&redirect_uri=http://localhost:8080/kakaologin&response_type=code";
    }

    @GetMapping("/kakaologin")
    public RedirectView kakaoCallback (@RequestParam String code) {
        String accessToken = oAuthService.getKakaoAccessToken(code);
        KakaoDto kakaoDto = oAuthService.getKakaoUserInfo(accessToken);
        Optional<User> user = userService.validateId(kakaoDto.getId());
        RedirectView redirectView = new RedirectView();
        if (!user.isEmpty()) {
            redirectView.addStaticAttribute("id", kakaoDto.getId());
        }
        redirectView.setUrl("/");
        return redirectView;
    }
}
