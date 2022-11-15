package ajou.gram.moim.controller;

import ajou.gram.moim.domain.MoimMember;
import ajou.gram.moim.domain.User;
import ajou.gram.moim.domain.UserMessage;
import ajou.gram.moim.domain.UserRegularSchedule;
import ajou.gram.moim.dto.JoinMoimDto;
import ajou.gram.moim.service.MoimService;
import ajou.gram.moim.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MoimService moimService;

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable("id") long id) {
        return userService.getUser(id);
    }

    @GetMapping("/schedule/{user_id}")
    public List<UserRegularSchedule> getUserRegularSchedule(@PathVariable("user_id") long userId) {
        return userService.getUserRegularSchedule(userId);
    }

    @PostMapping("/schedule/regular")
    public void addUserRegularSchedule(@RequestBody UserRegularSchedule userRegularSchedule) {
        userService.addUserRegularSchedule(userRegularSchedule);
    }

    @GetMapping("/message/{user_id}")
    public List<UserMessage> getMessages(@PathVariable("user_id") long userId) {
        return userService.getMessages(userId);
    }

    @PostMapping("/message/accept")
    public void moimJoinAccept(@RequestBody JoinMoimDto joinMoimDto) {
        MoimMember moimMember = new MoimMember();
        moimMember.setMoimId(joinMoimDto.getMoimId());
        moimMember.setUserId(joinMoimDto.getUserId());
        moimMember.setRegisterDate(new Date());
        moimMember.setLevel((short) 2);
        moimService.moimJoin(moimMember);
    }
}
