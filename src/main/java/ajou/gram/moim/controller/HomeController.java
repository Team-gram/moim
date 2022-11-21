package ajou.gram.moim.controller;

import ajou.gram.moim.domain.Category;
import ajou.gram.moim.domain.User;
import ajou.gram.moim.dto.JoinDto;
import ajou.gram.moim.dto.KakaoDto;
import ajou.gram.moim.service.OAuthService;
import ajou.gram.moim.service.SearchService;
import ajou.gram.moim.service.UserService;
import ajou.gram.moim.util.jwt.JsonWebToken;
import ajou.gram.moim.util.jwt.JwtAuthenticationFilter;
import ajou.gram.moim.util.jwt.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;


@Tag(name = "home", description = "로그인/회원가입 관련 API")
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final OAuthService oAuthService;
    private final SearchService searchService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Operation(summary = "POST() /join", description = "회원가입")
    @Parameters({
            @Parameter(name = "id", description = "카카오 고유 아이디(필수)", example = "2580109343"),
            @Parameter(name = "name", description = "이름 or 닉네임(필수)", example = "kim minsu"),
            @Parameter(name = "profileImage", description = "프로필 사진"),
            @Parameter(name = "sido", description = "시/도(필수)", example = "서울특별시 / 경기도"),
            @Parameter(name = "sigungu", description = "시/군/구(필수)", example = "수원시"),
            @Parameter(name = "dong", description = "동/읍/면(필수)", example = "원천동"),
            @Parameter(name = "gender", description = "성별(필수)", example = "M / W"),
            @Parameter(name = "birthday", description = "생년월일(필수)", example = "2000-01-01"),
            @Parameter(name = "detail", description = "자기소개"),
            @Parameter(name = "isPublish", description = "정보 공개 여부", example = "Y / N"),
            @Parameter(name = "categories", description = "카테고리", example = "[1, 2, 3]")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원가입 성공", content = @Content(schema = @Schema(implementation = JoinDto.class))),
            @ApiResponse(responseCode = "400", description = "회원가입 실패")
    })
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody JoinDto joinDto) {
        try {
            userService.addUser(joinDto);
            return ResponseEntity.ok().body(joinDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 중 오류가 발생하였습니다.");
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("입력한 값의 형식이 올바르지 않습니다.");
        }
    }

    @Operation(summary = "GET() /login", description = "로그인")
    @GetMapping("/login")
    public String login(HttpServletRequest request) throws UnknownHostException {
        String hostDomain = request.getRequestURL().toString();
        hostDomain = hostDomain.substring(0, hostDomain.lastIndexOf("/"));
        return "https://kauth.kakao.com/oauth/authorize?client_id=27769c331d08ceb2033e090a83e1e212&redirect_uri=" + hostDomain + "/kakaologin&response_type=code";
    }

    @Operation(summary = "GET() /kakaologin", description = "카카오 로그인 과정에서 호출되는 API로 프론트엔드에서는 사용하지 않는 API 임")
    @GetMapping("/kakaologin")
    public RedirectView kakaoCallback(@RequestParam String code, HttpServletRequest request) throws URISyntaxException {
        String accessToken = oAuthService.getKakaoAccessToken(code, request);
        KakaoDto kakaoDto = oAuthService.getKakaoUserInfo(accessToken);
        Optional<User> user = userService.validateId(kakaoDto.getId());
        if (user.isEmpty()) {
            userService.addUser(kakaoDto);
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(kakaoDto.getId(), kakaoDto.getId());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.createToken(authentication);

        RedirectView redirectView = new RedirectView();
        redirectView.addStaticAttribute("jwt", new JsonWebToken(jwt).getToken());
        redirectView.addStaticAttribute("id", kakaoDto.getId());
        redirectView.setUrl("/");
        return redirectView;
    }

    @Operation(summary = "GET() /category/{parentId}", description = "카테고리 호출")
    @Parameters({
            @Parameter(name = "parentId", description = "카테고리의 부모 아이디(상위 카테고리를 원하면 0)", example = "0")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "카테고리 조회 성공", content = @Content(schema = @Schema(implementation = Category.class))),
    })
    @GetMapping("/category/{parentId}")
    public ResponseEntity<List<Category>> getCategories(@PathVariable("parentId") int parentId) {
        return ResponseEntity.ok().body(searchService.getCategories(parentId));
    }

    @Operation(summary = "GET() /category/name/{categoryId}", description = "카테고리 이름 호출")
    @Parameters({
            @Parameter(name = "categoryId", description = "카테고리 아이디", example = "40")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "카테고리 이름 조회 성공", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/category/name/{categoryId}")
    public ResponseEntity<String> getCategoryName(@PathVariable("categoryId") int categoryId) {
        return ResponseEntity.ok().body(searchService.getCategoryName(categoryId).getCategoryName());
    }
}
