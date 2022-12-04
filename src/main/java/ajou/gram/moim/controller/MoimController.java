package ajou.gram.moim.controller;

import ajou.gram.moim.domain.*;
import ajou.gram.moim.dto.*;
import ajou.gram.moim.service.AwsS3Service;
import ajou.gram.moim.service.MoimDetailService;
import ajou.gram.moim.service.MoimService;
import ajou.gram.moim.service.MoimUpperService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Tag(name = "moim", description = "모임방 관련 API")
@RestController
@RequestMapping("/moim")
@RequiredArgsConstructor
public class MoimController {

    private final MoimService moimService;
    private final AwsS3Service awsS3Service;
    private final MoimDetailService moimDetailService;
    private final MoimUpperService moimUpperService;

    @Operation(summary = "GET() /moim", description = "모임방 조회 & 검색")
    @Parameters({
            @Parameter(name = "categoryId", description = "카테고리 상위/하위 아이디", example = "1"),
            @Parameter(name = "sido", description = "시/도", example = "서울특별시"),
            @Parameter(name = "sigungu", description = "시/군/구", example = "강남구"),
            @Parameter(name = "dong", description = "동/읍/면", example = "삼성동"),
            @Parameter(name = "title", description = "모임방 이름", example = "즐거운 모임")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임 조회 성공", content = @Content(schema = @Schema(implementation = Moim.class)))
    })
    @GetMapping("")
    public ResponseEntity<List<Moim>> getMoims(@RequestParam(value = "categoryId", required = false, defaultValue = "0") int categoryId,
                                              @RequestParam(value = "sido", required = false) String sido,
                                              @RequestParam(value = "sigungu", required = false) String sigungu,
                                              @RequestParam(value = "dong", required = false) String dong,
                                              @RequestParam(value = "title", required = false) String title) {
        return ResponseEntity.ok().body(moimService.getMoims(categoryId, sido, sigungu, dong, title));
    }

    @Operation(summary = "GET() /moim/member/{moimId}", description = "모임방에 가입한 유저 조회")
    @Parameters({
            @Parameter(name = "moimId", description = "모임방 아이디(필수)", example = "1")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임 유저 리스트 조회 성공", content = @Content(schema = @Schema(implementation = MoimMember.class)))
    })
    @GetMapping("/member/{moimId}")
    public ResponseEntity<List<MoimMember>> getMoims(@PathVariable("moimId") long moimId) {
        return ResponseEntity.ok().body(moimService.getMoimMembers(moimId));
    }

    @Operation(summary = "POST() /moim", description = "모임방 개설")
    @Parameters({
            @Parameter(name = "userId", description = "모임방 개설자 아이디(필수)", example = "2501213540"),
            @Parameter(name = "categoryId", description = "카테고리 하위 아이디(필수)", example = "1"),
            @Parameter(name = "title", description = "모임방 이름(필수)", example = "즐거운 모임"),
            @Parameter(name = "content", description = "모임방 설명", example = "친목 모임 입니다."),
            @Parameter(name = "sido", description = "시/도(필수)", example = "서울특별시"),
            @Parameter(name = "sigungu", description = "시/군/구(필수)", example = "강남시"),
            @Parameter(name = "dong", description = "동/읍/면(필수)", example = "삼성동"),
            @Parameter(name = "isPublish", description = "모임방 공개 여부(필수)", example = "Y / N"),
            @Parameter(name = "isFreeEnter", description = "자유 가입 여부(필수)", example = "Y / N"),
            @Parameter(name = "maxMember", description = "모임방 최대 인원 수(필수)", example = "30")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임방 개설 성공", content = @Content(schema = @Schema(implementation = CreateMoimDto.class))),
            @ApiResponse(responseCode = "400", description = "모임방 개설 실패")
    })
    @PostMapping("")
    public ResponseEntity<?> addMoim(@RequestBody CreateMoimDto createMoimDto) {
        try {
            moimService.addMoim(createMoimDto);
            return ResponseEntity.ok().body(createMoimDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("모임방 개설에 실패하였습니다.");
        }
    }

    @Operation(summary = "PUT() /moim", description = "모임방 정보 수정")
    @Parameters({
            @Parameter(name = "id", description = "모임방 아이디(필수)", example = "1"),
            @Parameter(name = "userId", description = "유저 아이디(필수)", example = "1234567890"),
            @Parameter(name = "categoryId", description = "카테고리 하위 아이디", example = "1"),
            @Parameter(name = "title", description = "모임방 이름", example = "즐거운 모임"),
            @Parameter(name = "content", description = "모임방 설명", example = "친목 모임 입니다."),
            @Parameter(name = "sido", description = "시/도", example = "서울특별시"),
            @Parameter(name = "sigungu", description = "시/군/구", example = "강남시"),
            @Parameter(name = "dong", description = "동/읍/면", example = "삼성동"),
            @Parameter(name = "isPublish", description = "모임방 공개 여부", example = "Y / N"),
            @Parameter(name = "isFreeEnter", description = "자유 가입 여부", example = "Y / N"),
            @Parameter(name = "maxMember", description = "모임방 최대 인원 수", example = "30")
    })
    @PutMapping("")
    public ResponseEntity<?> updateMoim(@RequestBody Moim moim) {
        moimService.updateMoim(moim);
        return ResponseEntity.ok().body(moim);
    }

    @Operation(summary = "PUT() /moim/thumbnail/{moimId}", description = "모임방 썸네일 수정")
    @Parameters({
            @Parameter(name = "id", description = "모임방 아이디(필수)", example = "1"),
            @Parameter(name = "thumbnail", description = "모임방 썸네일 이미지(필수)", example = "abc.jpg")
    })
    @PutMapping("/thumbnail/{moimId}")
    public ResponseEntity<?> updateMoimThumbnail(@PathVariable("moimId") long moimId,
                                                 @RequestPart("thumbnail") MultipartFile multipartFile) {
        moimService.updateMoimThumbnail(moimId, multipartFile);
        return ResponseEntity.ok().body("업데이트 성공");
    }

    @Operation(summary = "POST() /moim/free", description = "모임방 자유 가입")
    @Parameters({
            @Parameter(name = "moimId", description = "모임방 아이디(필수)", example = "1"),
            @Parameter(name = "userId", description = "가입하고자 하는 유저 아이디(필수)", example = "2501238503")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "자유 가입 성공", content = @Content(schema = @Schema(implementation = MoimMember.class))),
            @ApiResponse(responseCode = "400", description = "자유 가입 실패")
    })
    @PostMapping("/free")
    public ResponseEntity<?> moimJoin(@RequestBody MoimMember moimMember) {
        try {
            moimService.moimJoin(moimMember);
            return ResponseEntity.ok().body(moimMember);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("가입에 실패하였습니다.");
        }
    }

    @Operation(summary = "GET() /moim/message/check/{fromId}/{toId}/{type}", description = "모임방 가입/초대 메세지 중복 체크")
    @Parameters({
            @Parameter(name = "moimId", description = "모임 아이디(필수)", example = "1"),
            @Parameter(name = "toId", description = "메세지 수신자 아이디(필수)", example = "1234567890"),
            @Parameter(name = "type", description = "메세지 타입(필수)", example = "JOIN or INVITE")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임방 가입/초대 메세지 이력 조회 성공"),
            @ApiResponse(responseCode = "400", description = "이미 모임에 가입하였거나 메세지를 보낸 사용자 입니다.")
    })
    @GetMapping("/message/check/{moimId}/{toId}/{type}")
    public ResponseEntity<?> getMessageCheck(@PathVariable("moimId") long moimId,
                                             @PathVariable("toId") long toId,
                                             @PathVariable("type") String type) {
        UserMessage userMessage = moimService.getMessageCheck(moimId, toId, type);
        if (userMessage == null) {
            return ResponseEntity.ok().body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 모임에 가입하였거나 메세지를 보낸 사용자 입니다.");
        }
    }

    @Operation(summary = "POST() /moim/pass", description = "모임방 가입 신청")
    @Parameters({
            @Parameter(name = "moimId", description = "모임방 아이디(필수)", example = "1"),
            @Parameter(name = "userId", description = "가입하고자 하는 유저 아이디(필수)", example = "2501238503"),
            @Parameter(name = "message", description = "가입 신청 메세지", example = "안녕하세요, 모임 가입하고 싶어요.")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "가입 신청 성공", content = @Content(schema = @Schema(implementation = JoinMoimDto.class))),
            @ApiResponse(responseCode = "400", description = "가입 신청 실패")
    })
    @PostMapping("/pass")
    public ResponseEntity<?> moimJoinMessage(@RequestBody JoinMoimDto joinMoimDto) {
        try {
            moimService.moimJoinMessage(joinMoimDto);
            return ResponseEntity.ok().body(joinMoimDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("가입 신청을 실패하였습니다.");
        }
    }

    @Operation(summary = "POST() /moim/invite", description = "모임방 초대")
    @Parameters({
            @Parameter(name = "moimId", description = "모임방 아이디(필수)", example = "1"),
            @Parameter(name = "userId", description = "초대하고자 하는 유저 아이디(필수)", example = "2501238503"),
            @Parameter(name = "message", description = "초대 메세지", example = "모임에 초대합니다~")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "초대 신청 성공", content = @Content(schema = @Schema(implementation = JoinMoimDto.class))),
            @ApiResponse(responseCode = "400", description = "초대 신청 실패")
    })
    @PostMapping("/invite")
    public ResponseEntity<?> moimInviteMessage(@RequestBody JoinMoimDto joinMoimDto) {
        try {
            moimService.moimInviteMessage(joinMoimDto);
            return ResponseEntity.ok().body(joinMoimDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("초대 신청을 실패하였습니다.");
        }
    }

    @Operation(summary = "GET() /moim/{id}", description = "모임방 상세 조회")
    @Parameters({
            @Parameter(name = "id", description = "모임방 아이디(필수)", example = "1")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임방 상제 조회 성공", content = @Content(schema = @Schema(implementation = Moim.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Moim>> getMoim(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(moimService.getMoim(id));
    }

    @Operation(summary = "GET() /moim/chat/{moimId}", description = "모임방 채팅 조회")
    @Parameters({
            @Parameter(name = "moimId", description = "모임방 아이디(필수)", example = "1")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임방 채팅 조회 성공", content = @Content(schema = @Schema(implementation = PrintChatDto.class)))
    })
    @GetMapping("/chat/{moimId}")
    public ResponseEntity<List<PrintChatDto>> getMoimChats(@PathVariable("moimId") long moimId) throws SQLException {
        return ResponseEntity.ok().body(moimService.getMoimChats(moimId));
    }

    @Operation(summary = "POST() /moim/chat", description = "모임방 채팅 입력")
    @Parameters({
            @Parameter(name = "moimId", description = "모임방 아이디(필수)", example = "1"),
            @Parameter(name = "userId", description = "유저 아이디(필수)", example = "2501238503"),
            @Parameter(name = "content", description = "채팅 내용(필수)", example = "ㅋㅋㅋㅋ")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "가입 신청 성공", content = @Content(schema = @Schema(implementation = ChatDto.class))),
            @ApiResponse(responseCode = "400", description = "가입 신청 실패")
    })
    @PostMapping("/chat")
    public ResponseEntity<?> addChat(@RequestBody ChatDto chatDto) {
        try {
            moimService.addChat(chatDto);
            return ResponseEntity.ok().body(chatDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("채팅 등록에 실패하였습니다.");
        }
    }

    @Operation(summary = "GET() /moim/schedule/{moimId}/{type}", description = "모임 일정 조회")
    @Parameters({
            @Parameter(name = "moimId", description = "모임 아이디(필수)", example = "1"),
            @Parameter(name = "type", description = "일정 조회 타입(0: 전체, 1:정기, 2:비정기)", example = "0")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임 일정 조회 성공", content = @Content(schema = @Schema(implementation = MoimRegularSchedule.class)))
    })
    @GetMapping("/schedule/{moimId}/{type}")
    public ResponseEntity<?> getMoimRegularSchedule(@PathVariable("moimId") long moimId,
                                                    @PathVariable("type") int type) {
        JSONObject jsonObject = new JSONObject();
        switch (type) {
            case 1:
                jsonObject.put("regular", moimDetailService.getMoimRegularSchedule(moimId));
                return ResponseEntity.ok().body(jsonObject);
//            case 2:
//                jsonObject.put("irregular", userService.getUserIrregularSchedule(userId));
//                return ResponseEntity.ok().body(userService.getUserIrregularSchedule(userId));
//            default:
//                jsonObject.put("regular", userService.getUserRegularSchedule(userId));
//                jsonObject.put("irregular", userService.getUserIrregularSchedule(userId));
//                return ResponseEntity.ok().body(jsonObject);
        }
        return ResponseEntity.ok().body("~");
    }

    @Operation(summary = "POST() /moim/regular", description = "모임 정기 일정 등록")
    @Parameters({
            @Parameter(name = "moimId", description = "모임 아이디(필수)", example = "1"),
            @Parameter(name = "day", description = "요일(필수) [0:월요일, 1:화요일, 2:수요일, ..., 6:일요일]", example = "0"),
            @Parameter(name = "startTime", description = "일정 시작 시간(필수) [hh:mm]", example = "08:30"),
            @Parameter(name = "endTime", description = "일정 종료 시간(필수) [hh:mm]", example = "12:00"),
            @Parameter(name = "scheduleName", description = "일정 이름(필수)", example = "등산"),
            @Parameter(name = "scheduleDetail", description = "일정 설명", example = "정기 등산 하는 날 입니다~")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임 정기 일정 등록 성공", content = @Content(schema = @Schema(implementation = CreateMoimRegularScheduleDto.class))),
            @ApiResponse(responseCode = "400", description = "모임 정기 일정 등록 실패")
    })
    @PostMapping("/regular")
    public ResponseEntity<?> addMoimRegularSchedule(@RequestBody CreateMoimRegularScheduleDto createMoimRegularScheduleDto) {
        try {
            moimDetailService.addMoimRegularSchedule(createMoimRegularScheduleDto);
            return ResponseEntity.ok().body(createMoimRegularScheduleDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("일정 등록에 실패하였습니다.");
        }
    }

    @Operation(summary = "PUT() /moim/regular/{moimId}/{scheduleId}", description = "모임 정기 일정 수정")
    @Parameters({
            @Parameter(name = "moimId", description = "모임 아이디(필수)", example = "1"),
            @Parameter(name = "scheduleId", description = "일정 아이디(필수)", example = "1"),
            @Parameter(name = "day", description = "요일(필수) [0:일요일, 1:월요일, 2:화요일, ...]", example = "0"),
            @Parameter(name = "startTime", description = "일정 시작 시간(필수) [hh:mm]", example = "08:30"),
            @Parameter(name = "endTime", description = "일정 종료 시간(필수) [hh:mm]", example = "12:00"),
            @Parameter(name = "scheduleName", description = "일정 이름(필수)", example = "등산"),
            @Parameter(name = "scheduleDetail", description = "일정 설명", example = "백두산 탈 사람")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임 정기 일정 수정 성공", content = @Content(schema = @Schema(implementation = CreateMoimRegularScheduleDto.class))),
            @ApiResponse(responseCode = "400", description = "모임 정기 일정 수정 실패")
    })
    @PutMapping("/regular/{moimId}/{scheduleId}")
    public ResponseEntity<?> updateMoimRegularSchedule(@PathVariable("moimId") long moimId,
                                                       @PathVariable("scheduleId") long scheduleId,
                                                       @RequestBody CreateMoimRegularScheduleDto createMoimRegularScheduleDto) {
        moimDetailService.updateMoimRegularSchedule(moimId, scheduleId, createMoimRegularScheduleDto);
        return ResponseEntity.ok().body(createMoimRegularScheduleDto);
    }

    @Operation(summary = "DELETE() /moim/regular/{moimId}/{scheduleId}", description = "모임 정기 일정 삭제")
    @Parameters({
            @Parameter(name = "moimId", description = "모임 아이디(필수)", example = "1"),
            @Parameter(name = "scheduleId", description = "일정 아이디(필수)", example = "1")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임 정기 일정 삭제 성공", content = @Content(schema = @Schema(implementation = MoimRegularSchedule.class)))
    })
    @DeleteMapping("/regular/{moimId}/{scheduleId}")
    public ResponseEntity<MoimRegularSchedule> deleteMoimRegularSchedule(@PathVariable("moimId") long moimId,
                                                                         @PathVariable("scheduleId") long scheduleId) {
        moimDetailService.deleteMoimRegularSchedule(moimId, scheduleId);
        MoimRegularSchedule moimRegularSchedule = new MoimRegularSchedule();
        moimRegularSchedule.setId(scheduleId);
        moimRegularSchedule.setMoimId(moimId);
        return ResponseEntity.ok().body(moimRegularSchedule);
    }

    @Operation(summary = "PUT() /moim/member/level", description = "모임원 등급 변경")
    @Parameters({
            @Parameter(name = "moimId", description = "모임 아이디(필수)", example = "1"),
            @Parameter(name = "userId", description = "유저 아이디(필수)", example = "1234567890"),
            @Parameter(name = "level", description = "유저 등급 (2: 방장, 1: 부방장, 0:일반 모임원", example = "0"),
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임원 등급 변경 성공", content = @Content(schema = @Schema(implementation = MoimMember.class))),
            @ApiResponse(responseCode = "400", description = "모임원 등급 변경 실패")
    })
    @PutMapping("/member/level")
    public ResponseEntity<?> updateMemberLevel(@RequestBody MoimMember moimMember) {
        try {
            moimService.updateMemberLevel(moimMember);
            return ResponseEntity.ok().body(moimMember);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("일정 등록에 실패하였습니다.");
        }
    }

    @Operation(summary = "DELETE() /moim/member/banish", description = "모임원 강퇴")
    @Parameters({
            @Parameter(name = "moimId", description = "모임 아이디(필수)", example = "1"),
            @Parameter(name = "userId", description = "강퇴할 유저 아이디(필수)", example = "1234567890")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임원 강퇴 성공", content = @Content(schema = @Schema(implementation = MoimMember.class))),
            @ApiResponse(responseCode = "400", description = "모임원 강퇴 실패")
    })
    @DeleteMapping("/member/banish")
    public ResponseEntity<?> banishMember(@RequestBody MoimMember moimMember) {
        try {
            moimService.banishMember(moimMember);
            return ResponseEntity.ok().body(moimMember);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("일정 등록에 실패하였습니다.");
        }
    }

    @Operation(summary = "POST() /moim/upper", description = "상위노출 모임 신청")
    @Parameters({
            @Parameter(name = "moimId", description = "모임 아이디(필수)", example = "1"),
            @Parameter(name = "period", description = "상위노출 기간(필수), 일 단위", example = "180 / 90 / 30"),
            @Parameter(name = "money", description = "금액(필수)", example = "140000 / 70000 / 25000")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "상위노출 모임 신청 성공", content = @Content(schema = @Schema(implementation = MoimUpperDto.class))),
            @ApiResponse(responseCode = "400", description = "상위노출 모임 신청 실패")
    })
    @PostMapping("/upper")
    public ResponseEntity<?> upperMoim(@RequestBody MoimUpperDto moimUpperDto) {
        try {
            boolean isAddPossible = moimUpperService.checkLimitUpperMoim(moimUpperDto);
            if (isAddPossible) {
                moimUpperService.addUpperMoim(moimUpperDto);
                return ResponseEntity.ok().body(moimUpperDto);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("신청에 실패하였습니다. 관리자에게 문의해주세요.");
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("신청에 실패하였습니다.");
        }
    }

    @Operation(summary = "POST() /moim/upper/history", description = "상위노출 모임 히스토리")
    @Parameters({
            @Parameter(name = "moimId", description = "모임 아이디(필수)", example = "1")
    })
    @PostMapping("/upper/history")
    public ResponseEntity<?> upperMoimHistory(@RequestBody MoimUpperDto moimUpperDto) {
        moimUpperService.addUpperMoimHistory(moimUpperDto);
        return ResponseEntity.ok().body("ok");
    }

    @Operation(summary = "GET() /moim/upper/{categoryId}", description = "상위노출 모임 리스트 조회")
    @Parameters({
            @Parameter(name = "categoryId", description = "카테고리 아이디(입력 안할시 랜덤으로 값 대입)", example = "1")
    })
    @GetMapping({"/upper/{categoryId}", "/upper"})
    public ResponseEntity<?> getUpperMoims(@PathVariable(value = "categoryId", required = false) Integer categoryId) throws SQLException {
        if (categoryId == null) {
            double min = 19;
            double max = 223;
            double random = (int) ((Math.random() * (max - min)) + min);
            categoryId = (int) random;
        }

        return ResponseEntity.ok().body(moimUpperService.getUpperMoims(categoryId));
    }

    @Operation(summary = "GET() /moim/upper/detail/{moimId}", description = "상위노출 상태 조회")
    @Parameters({
            @Parameter(name = "moimId", description = "모임 아이디(필수)", example = "1")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "상위노출 상태 조회 성공", content = @Content(schema = @Schema(implementation = MoimUpper.class)))
    })
    @GetMapping("/upper/detail/{moimId}")
    public ResponseEntity<?> getUpperMoimStatus(@PathVariable("moimId") long moimId) {
        return ResponseEntity.ok().body(moimUpperService.getUpperMoimStatus(moimId));
    }

    @Operation(summary = "DELETE() /moim/leave", description = "모임 탈퇴")
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디", example = "123123123"),
            @Parameter(name = "moimId", description = "모임 아이디", example = "1")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임 탈퇴 성공")
    })
    @DeleteMapping("/leave")
    public ResponseEntity<?> leaveMoim(@RequestBody LeaveMoimDto leaveMoimDto) {
        moimService.leaveMoim(leaveMoimDto);
    }
}
