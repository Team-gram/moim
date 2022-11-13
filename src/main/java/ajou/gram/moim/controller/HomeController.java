package ajou.gram.moim.controller;

import ajou.gram.moim.domain.User;
import ajou.gram.moim.dto.JoinDto;
import ajou.gram.moim.dto.KakaoDto;
import ajou.gram.moim.dto.LoginDto;
import ajou.gram.moim.service.OAuthService;
import ajou.gram.moim.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Tag(name = "home", description = "로그인/회원가입 관련 API")
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final OAuthService oAuthService;

    @Operation(summary = "POST() join", description = "회원가입")
    @Parameters({
            @Parameter(name = "id", description = "카카오 고유 아이디(필수)", example = "2580109343"),
            @Parameter(name = "name", description = "이름 or 닉네임(필수)", example = "kim minsu"),
            @Parameter(name = "profileImage", description = "프로필 사진"),
            @Parameter(name = "phone", description = "전화번호(필수)", example = "01011112222"),
            @Parameter(name = "sido", description = "시/도(필수)", example = "서울특별시, 경기도"),
            @Parameter(name = "sigungu", description = "시/군/구(필수)", example = "수원시"),
            @Parameter(name = "dong", description = "동/읍/면(필수)", example = "원천동"),
            @Parameter(name = "gender", description = "성별(필수)", example = "M / W"),
            @Parameter(name = "birthday", description = "생년월일(필수)", example = "2000-01-01"),
            @Parameter(name = "detail", description = "자기소개"),
            @Parameter(name = "isPublish", description = "정보 공개 여부", example = "Y / N"),
            @Parameter(name = "categories", description = "카테고리", example = "[1, 2, 3]")
    })
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
