package ajou.gram.moim.controller;

import ajou.gram.moim.domain.*;
import ajou.gram.moim.dto.CreateRegularScheduleDto;
import ajou.gram.moim.dto.JoinMoimDto;
import ajou.gram.moim.dto.RecommendMoimDto;
import ajou.gram.moim.service.MoimService;
import ajou.gram.moim.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "유저 조회 성공", content = @Content(schema = @Schema(implementation = User.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(userService.getUser(id));
    }

    @Operation(summary = "GET() /user/moim/{userId}", description = "유저가 가입한 모임 조회")
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디(필수)", example = "2506012341")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임 조회 성공", content = @Content(schema = @Schema(implementation = Moim.class)))
    })
    @GetMapping("/moim/{userId}")
    public ResponseEntity<List<Optional<Moim>>> getUserMoims(@PathVariable("userId") long userId) {
        return ResponseEntity.ok().body(moimService.getMoims(userId));
    }

    @Operation(summary = "GET() /user/schedule/{userId}/{type}", description = "유저 개인 일정 조회")
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디(필수)", example = "2506012341"),
            @Parameter(name = "type", description = "일정 조회 타입(0: 전체, 1:정기, 2:비정기)", example = "0")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "개인 일정 조회 성공", content = @Content(schema = @Schema(implementation = UserRegularSchedule.class)))
    })
    @GetMapping("/schedule/{userId}/{type}")
    public ResponseEntity<?> getUserRegularSchedule(@PathVariable("userId") long userId,
                                                    @PathVariable("type") int type) {
        JSONObject jsonObject = new JSONObject();
        switch (type) {
            case 1:
                jsonObject.put("regular", userService.getUserRegularSchedule(userId));
                return ResponseEntity.ok().body(userService.getUserRegularSchedule(userId));
            case 2:
                jsonObject.put("irregular", userService.getUserIrregularSchedule(userId));
                return ResponseEntity.ok().body(userService.getUserIrregularSchedule(userId));
            default:
                jsonObject.put("regular", userService.getUserRegularSchedule(userId));
                jsonObject.put("irregular", userService.getUserIrregularSchedule(userId));
                return ResponseEntity.ok().body(jsonObject);
        }
    }

    @Operation(summary = "POST() /user/regular", description = "유저 정기 일정 등록")
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디(필수)", example = "2506012341"),
            @Parameter(name = "day", description = "요일(필수) [0:월요일, 1:화요일, 2:수요일, ..., 6:일요일]", example = "0"),
            @Parameter(name = "startTime", description = "일정 시작 시간(필수) [hh:mm]", example = "08:30"),
            @Parameter(name = "endTime", description = "일정 종료 시간(필수) [hh:mm]", example = "12:00"),
            @Parameter(name = "title", description = "일정 이름(필수)", example = "공부"),
            @Parameter(name = "detail", description = "일정 설명", example = "시험 공부 하는 시간")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정기 일정 등록 성공", content = @Content(schema = @Schema(implementation = CreateRegularScheduleDto.class))),
            @ApiResponse(responseCode = "400", description = "정기 일정 등록 실패")
    })
    @PostMapping("/regular")
    public ResponseEntity<?> addUserRegularSchedule(@RequestBody CreateRegularScheduleDto createRegularScheduleDto) {
        boolean duplicate = userService.validateShedule(createRegularScheduleDto.getUserId(), 0, createRegularScheduleDto);
        try {
            if (duplicate) {
                userService.addUserRegularSchedule(createRegularScheduleDto);
                return ResponseEntity.ok().body(createRegularScheduleDto);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("중복된 일정입니다.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("일정 등록에 실패하였습니다.");
        }
    }

    @Operation(summary = "POST() /user/irregular", description = "유저 비정기 일정 등록")
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디(필수)", example = "2506012341"),
            @Parameter(name = "date", description = "날짜(필수)", example = "2022-01-01"),
            @Parameter(name = "startTime", description = "일정 시작 시간(필수) [hh:mm]", example = "08:30"),
            @Parameter(name = "endTime", description = "일정 종료 시간(필수) [hh:mm]", example = "12:00"),
            @Parameter(name = "title", description = "일정 이름(필수)", example = "공부"),
            @Parameter(name = "detail", description = "일정 설명", example = "시험 공부 하는 시간")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "비정기 일정 등록 성공", content = @Content(schema = @Schema(implementation = UserIrregularSchedule.class))),
            @ApiResponse(responseCode = "400", description = "비정기 일정 등록 실패")
    })
    @PostMapping("/irregular")
    public ResponseEntity<?> addUserIrregularSchedule(@RequestBody UserIrregularSchedule userIrregularSchedule) {
        try {
            userService.addUserIrregularSchedule(userIrregularSchedule);
            return ResponseEntity.ok().body(userIrregularSchedule);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("일정 등록에 실패하였습니다.");
        }
    }

    @Operation(summary = "PATCH() /user/regular/{userId}/{scheduleId}", description = "개인 정기 일정 수정")
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디(필수)", example = "2506012341"),
            @Parameter(name = "scheduleId", description = "일정 아이디(필수)", example = "1"),
            @Parameter(name = "day", description = "요일(필수) [0:일요일, 1:월요일, 2:화요일, ...]", example = "0"),
            @Parameter(name = "startTime", description = "일정 시작 시간(필수) [hh:mm]", example = "08:30"),
            @Parameter(name = "endTime", description = "일정 종료 시간(필수) [hh:mm]", example = "12:00"),
            @Parameter(name = "title", description = "일정 이름(필수)", example = "공부"),
            @Parameter(name = "detail", description = "일정 설명", example = "시험 공부 하는 시간")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "개인 정기 일정 수정 성공", content = @Content(schema = @Schema(implementation = CreateRegularScheduleDto.class))),
            @ApiResponse(responseCode = "400", description = "중복 일정이 존재하여 수정 실패")
    })
    @PatchMapping("/regular/{userId}/{scheduleId}")
    public ResponseEntity<?> updateUserRegularSchedule(@PathVariable("userId") long userId,
                                                       @PathVariable("scheduleId") long scheduleId,
                                                       @RequestBody CreateRegularScheduleDto createRegularScheduleDto) {
        boolean duplicate = userService.validateShedule(userId, scheduleId, createRegularScheduleDto);
        if (duplicate) {
            userService.updateUserRegularSchedule(userId, scheduleId, createRegularScheduleDto);
            return ResponseEntity.ok().body(createRegularScheduleDto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("중복된 일정입니다.");
    }

    @Operation(summary = "PATCH() /user/irregular/{userId}/{scheduleId}", description = "개인 비정기 일정 수정")
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디(필수)", example = "2506012341"),
            @Parameter(name = "scheduleId", description = "일정 아이디(필수)", example = "1"),
            @Parameter(name = "date", description = "날짜(필수)", example = "2022-01-01"),
            @Parameter(name = "startTime", description = "일정 시작 시간(필수) [hh:mm]", example = "08:30"),
            @Parameter(name = "endTime", description = "일정 종료 시간(필수) [hh:mm]", example = "12:00"),
            @Parameter(name = "title", description = "일정 이름(필수)", example = "공부"),
            @Parameter(name = "detail", description = "일정 설명", example = "시험 공부 하는 시간")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "개인 비정기 일정 수정 성공", content = @Content(schema = @Schema(implementation = UserIrregularSchedule.class))),
            @ApiResponse(responseCode = "400", description = "개인 비정기 일정 수정 실패")
    })
    @PatchMapping("/irregular/{userId}/{scheduleId}")
    public ResponseEntity<?> updateUserIrregularSchedule(@PathVariable("userId") long userId,
                                                         @PathVariable("scheduleId") long scheduleId,
                                                         @RequestBody UserIrregularSchedule userIrregularSchedule) {
        try {
            userService.updateUserIrregularSchedule(userId, scheduleId, userIrregularSchedule);
            return ResponseEntity.ok().body(userIrregularSchedule);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("입력한 값의 형식이 올바르지 않습니다.");
        }
    }

    @Operation(summary = "DELETE() /user/regular/{userId}/{scheduleId}", description = "개인 정기 일정 삭제")
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디(필수)", example = "2506012341"),
            @Parameter(name = "scheduleId", description = "일정 아이디(필수)", example = "1")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "개인 정기 일정 삭제 성공", content = @Content(schema = @Schema(implementation = UserRegularSchedule.class)))
    })
    @DeleteMapping("/regular/{userId}/{scheduleId}")
    public ResponseEntity<UserRegularSchedule> deleteUserRegularSchedule(@PathVariable("userId") long userId,
                                                                         @PathVariable("scheduleId") long scheduleId) {
        userService.deleteUserRegularSchedule(userId, scheduleId);
        UserRegularSchedule userRegularSchedule = new UserRegularSchedule();
        userRegularSchedule.setId(scheduleId);
        userRegularSchedule.setUserId(userId);
        return ResponseEntity.ok().body(userRegularSchedule);
    }

    @Operation(summary = "DELETE() /user/irregular/{userId}/{scheduleId}", description = "개인 비정기 일정 삭제")
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디(필수)", example = "2506012341"),
            @Parameter(name = "scheduleId", description = "일정 아이디(필수)", example = "1")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "개인 비정기 일정 삭제 성공", content = @Content(schema = @Schema(implementation = UserIrregularSchedule.class)))
    })
    @DeleteMapping("/irregular/{userId}/{scheduleId}")
    public ResponseEntity<UserIrregularSchedule> deleteUserIrregularSchedule(@PathVariable("userId") long userId,
                                                                           @PathVariable("scheduleId") long scheduleId) {
        userService.deleteUserIrregularSchedule(userId, scheduleId);
        UserIrregularSchedule userIrregularSchedule = new UserIrregularSchedule();
        userIrregularSchedule.setId(scheduleId);
        userIrregularSchedule.setUserId(userId);
        return ResponseEntity.ok().body(userIrregularSchedule);
    }

    @Operation(summary = "GET() /user/schedule/regular/{userId}/{scheduleId}", description = "개인 정기 일정 상세 조회")
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디(필수)", example = "2506012341"),
            @Parameter(name = "scheduleId", description = "일정 아이디(필수)", example = "1")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "개인 정기 일정 상세 조회 성공", content = @Content(schema = @Schema(implementation = UserRegularSchedule.class)))
    })
    @GetMapping("/schedule/regular/{userId}/{scheduleId}")
    public ResponseEntity<Optional<UserRegularSchedule>> getUserRegularScheduleDetail(@PathVariable("userId") long userId,
                                                                                  @PathVariable("scheduleId") long scheduleId) {
        return ResponseEntity.ok().body(userService.getUserRegularScheduleDetail(userId, scheduleId));
    }

    @Operation(summary = "GET() /user/schedule/irregular/{userId}/{scheduleId}", description = "개인 비정기 일정 상세 조회")
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디(필수)", example = "2506012341"),
            @Parameter(name = "scheduleId", description = "일정 아이디(필수)", example = "1")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "개인 비정기 일정 상세 조회 성공", content = @Content(schema = @Schema(implementation = UserIrregularSchedule.class)))
    })
    @GetMapping("/schedule/irregular/{userId}/{scheduleId}")
    public ResponseEntity<Optional<UserIrregularSchedule>> getUserIrregularScheduleDetail(@PathVariable("userId") long userId,
                                                                                          @PathVariable("scheduleId") long scheduleId) {
        return ResponseEntity.ok().body(userService.getUserIrregularScheduleDetail(userId, scheduleId));
    }

    @Operation(summary = "GET() /user/message/{userId}", description = "메세지 조회")
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디(필수)", example = "2506012341")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "메세지 조회 성공", content = @Content(schema = @Schema(implementation = UserMessage.class)))
    })
    @GetMapping("/message/{userId}")
    public ResponseEntity<List<UserMessage>> getMessages(@PathVariable("userId") long userId) {
        return ResponseEntity.ok().body(userService.getMessages(userId));
    }

    @Operation(summary = "POST() /user/message/accept", description = "모임방 가입 승인")
    @Parameters({
            @Parameter(name = "moimId", description = "모임방 아이디(필수)", example = "1"),
            @Parameter(name = "userId", description = "가입 승인할 유저 아이디(필수)", example = "2506012341")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "가입 승인 성공", content = @Content(schema = @Schema(implementation = JoinMoimDto.class))),
            @ApiResponse(responseCode = "400", description = "가입 승인 실패")
    })
    @PostMapping("/message/accept")
    public ResponseEntity<?> moimJoinAccept(@RequestBody JoinMoimDto joinMoimDto) {
        MoimMember moimMember = new MoimMember();
        moimMember.setMoimId(joinMoimDto.getMoimId());
        moimMember.setUserId(joinMoimDto.getUserId());
        try {
            moimService.moimJoin(moimMember);
            return ResponseEntity.ok().body(joinMoimDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("가입 승인을 실패하였습니다.");
        }
    }

    @Operation(summary = "GET() /user/recommend/{userId}", description = "추천 모임방 조회")
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디(필수)", example = "250601234")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "추천 모임방 조회 성공", content = @Content(schema = @Schema(implementation = RecommendMoimDto.class)))
    })
    @GetMapping("/recommend/{userId}")
    public ResponseEntity<?> recommendMoim(@PathVariable("userId") long userId) {
        Optional<User> user = userService.getUser(userId);
        JSONObject jsonObject = new JSONObject();
        user.ifPresent(selectedUser -> {
            try {
                jsonObject.put("recommendMoims", moimService.recommendMoim(selectedUser));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        return ResponseEntity.ok().body(jsonObject);
    }
}
