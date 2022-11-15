package ajou.gram.moim.controller;

import ajou.gram.moim.domain.MoimMember;
import ajou.gram.moim.domain.User;
import ajou.gram.moim.domain.UserMessage;
import ajou.gram.moim.domain.UserRegularSchedule;
import ajou.gram.moim.dto.CreateRegularScheduleDto;
import ajou.gram.moim.dto.JoinMoimDto;
import ajou.gram.moim.service.MoimService;
import ajou.gram.moim.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Tag(name = "user", description = "유저 관련 API")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MoimService moimService;

    @Operation(summary = "GET() /user/{id}", description = "유저 조회")
    @Parameters({
            @Parameter(name = "id", description = "유저 아이디(필수)", example = "2506012341")
    })
    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable("id") long id) {
        return userService.getUser(id);
    }

    @Operation(summary = "GET() /user/schedule/{userId}", description = "유저 개인 일정 조회 (정기일정만 조회 가능)")
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디(필수)", example = "2506012341")
    })
    @GetMapping("/schedule/{userId}")
    public List<UserRegularSchedule> getUserRegularSchedule(@PathVariable("userId") long userId) {
        return userService.getUserRegularSchedule(userId);
    }

    @Operation(summary = "POST() /user/schedule/regular", description = "유저 정기 일정 등록")
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디(필수)", example = "2506012341"),
            @Parameter(name = "day", description = "요일(필수) [0:일요일, 1:월요일, 2:화요일, ...]", example = "0"),
            @Parameter(name = "startTime", description = "일정 시작 시간(필수) [hhmm]", example = "08:30"),
            @Parameter(name = "endTime", description = "일정 종료 시간(필수) [hhmm]", example = "12:00"),
            @Parameter(name = "title", description = "일정 이름(필수)", example = "공부"),
            @Parameter(name = "detail", description = "일정 설명", example = "시험 공부 하는 시간")
    })
    @PostMapping("/schedule/regular")
    public void addUserRegularSchedule(@RequestBody CreateRegularScheduleDto createRegularScheduleDto) {
        userService.addUserRegularSchedule(createRegularScheduleDto);
    }

    @Operation(summary = "GET() /user/message/{userId}", description = "메세지 조회")
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디(필수)", example = "2506012341")
    })
    @GetMapping("/message/{userId}")
    public List<UserMessage> getMessages(@PathVariable("userId") long userId) {
        return userService.getMessages(userId);
    }

    @Operation(summary = "POST() /user/message/accept", description = "모임방 가입 승인")
    @Parameters({
            @Parameter(name = "moimId", description = "모임방 아이디", example = "1"),
            @Parameter(name = "userId", description = "가입 승인할 유저 아이디", example = "2506012341")
    })
    @PostMapping("/message/accept")
    public void moimJoinAccept(@RequestBody JoinMoimDto joinMoimDto) {
        MoimMember moimMember = new MoimMember();
        moimMember.setMoimId(joinMoimDto.getMoimId());
        moimMember.setUserId(joinMoimDto.getUserId());
        moimService.moimJoin(moimMember);
    }
}
