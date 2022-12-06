package ajou.gram.moim.controller;

import ajou.gram.moim.domain.MoimPlaceUpper;
import ajou.gram.moim.domain.MoimRegularSchedule;
import ajou.gram.moim.domain.MoimScheduleMember;
import ajou.gram.moim.domain.MoimScheduleReference;
import ajou.gram.moim.dto.*;
import ajou.gram.moim.service.MoimDetailService;
import ajou.gram.moim.service.MoimUpperService;
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
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Tag(name = "meet", description = "모임방 내부 API")
@RestController
@RequestMapping("/meet")
@RequiredArgsConstructor
public class MeetController {

    private final MoimDetailService moimDetailService;
    private final MoimUpperService moimUpperService;

    @Operation(summary = "GET() /meet/{moimId}", description = "모임 일정 조율")
    @Parameters({
            @Parameter(name = "moimId", description = "모임 아이디(필수)", example = "1"),
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임 일정 조율 성공", content = @Content(schema = @Schema(implementation = MoimMemberScheduleDto.class)))
    })
    @GetMapping("/{moimId}")
    public ResponseEntity<?> getMoimUserSchedules(@PathVariable("moimId") long moimId) throws SQLException {
        return ResponseEntity.ok().body(moimDetailService.getMoimUserSchedules(moimId));
    }

    @Operation(summary = "GET() /meet/{moimId}/{scheduleId}", description = "모임 일정에 참가한 멤버 리스트 조회")
    @Parameters({
            @Parameter(name = "moimId", description = "모임 아이디(필수)", example = "1"),
            @Parameter(name = "moimScheduleId", description = "모임 일정 아이디(필수)", example = "1")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임 일정에 참가한 멤버 리스트 조회 성공", content = @Content(schema = @Schema(implementation = MoimScheduleMemberDto.class)))
    })
    @GetMapping("/{moimId}/{moimScheduleId}")
    public ResponseEntity<?> getMoimUsers(@PathVariable("moimId") long moimId,
                                                  @PathVariable("moimScheduleId") long moimScheduleId) throws SQLException {
        return ResponseEntity.ok().body(moimDetailService.getMoimUsers(moimId, moimScheduleId));
    }

    @Operation(summary = "POST() /meet", description = "모임 일정 참가")
    @Parameters({
            @Parameter(name = "moimId", description = "모임 아이디(필수)", example = "1"),
            @Parameter(name = "moimScheduleId", description = "모임 일정 아이디(필수)", example = "1"),
            @Parameter(name = "userId", description = "유저 아이디(필수)", example = "1234567890")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임 일정 참가 성공", content = @Content(schema = @Schema(implementation = MoimScheduleMember.class)))
    })
    @PostMapping("")
    public ResponseEntity<?> moimScheduleJoin(@RequestBody JoinMoimScheduleDto joinMoimScheduleDto) {
        return ResponseEntity.ok().body(moimDetailService.moimScheduleJoin(joinMoimScheduleDto));
    }

    @Operation(summary = "DELETE() /meet", description = "모임 일정 참가 취소")
    @Parameters({
            @Parameter(name = "moimId", description = "모임 아이디(필수)", example = "1"),
            @Parameter(name = "moimScheduleId", description = "모임 일정 아이디(필수)", example = "1"),
            @Parameter(name = "userId", description = "유저 아이디(필수)", example = "1234567890")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임 일정 참가 취소 성공", content = @Content(schema = @Schema(implementation = MoimScheduleMember.class)))
    })
    @DeleteMapping("")
    public ResponseEntity<?> moimScheduleCancle(@RequestBody JoinMoimScheduleDto joinMoimScheduleDto) {
        moimDetailService.moimScheduleCancle(joinMoimScheduleDto);
        return ResponseEntity.ok().body("ok");
    }

    @Operation(summary = "GET() /meet/ref/{moimId}/{scheduleId}", description = "모임 일정 준비물 조회")
    @Parameters({
            @Parameter(name = "moimId", description = "모임 아이디(필수)", example = "1"),
            @Parameter(name = "moimScheduleId", description = "모임 일정 아이디(필수)", example = "1")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임 일정 준비물 조회 성공", content = @Content(schema = @Schema(implementation = MoimScheduleReferenceDto.class)))
    })
    @GetMapping("/ref/{moimId}/{moimScheduleId}")
    public ResponseEntity<?> getMoimScheduleReferences(@PathVariable("moimId") long moimId,
                                                       @PathVariable("moimScheduleId") long moimScheduleId) throws SQLException {
        return ResponseEntity.ok().body(moimDetailService.getMoimScheduleReferences(moimId, moimScheduleId));
    }

    @Operation(summary = "POST() /meet/ref", description = "모임 일정 준비물 추가")
    @Parameters({
            @Parameter(name = "moimId", description = "모임 아이디(필수)", example = "1"),
            @Parameter(name = "moimScheduleId", description = "모임 일정 아이디(필수)", example = "1"),
            @Parameter(name = "name", description = "준비물 이름(필수)", example = "볼펜")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임 일정 준비물 추가 성공")
    })
    @PostMapping("/ref")
    public ResponseEntity<?> addMoimScheduleReference(@RequestBody MoimScheduleReferenceDto moimScheduleReferenceDto) {
        moimDetailService.addMoimScheduleReference(moimScheduleReferenceDto);
        return ResponseEntity.ok().body("ok");
    }

    @Operation(summary = "PUT() /meet/ref", description = "모임 일정 준비물 챙기기")
    @Parameters({
            @Parameter(name = "id", description = "준비물 아이디(필수)", example = "1"),
            @Parameter(name = "userId", description = "유저 아이디(필수)", example = "1234567890")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임 일정 준비물 챙기기 성공")
    })
    @PutMapping("/ref")
    public ResponseEntity<?> updateMoimScheduleReference(@RequestBody MoimScheduleReference moimScheduleReference) {
        moimDetailService.updateMoimScheduleReference(moimScheduleReference);
        return ResponseEntity.ok().body("ok");
    }

    @Operation(summary = "DELETE() /meet/ref", description = "모임 일정 준비물 삭제")
    @Parameters({
            @Parameter(name = "id", description = "준비물 아이디(필수)", example = "1")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임 일정 준비물 삭제 성공")
    })
    @DeleteMapping("/ref")
    public ResponseEntity<?> deleteMoimScheduleReference(@RequestBody MoimScheduleReference moimScheduleReference) {
        moimDetailService.deleteMoimScheduleReference(moimScheduleReference);
        return ResponseEntity.ok().body("ok");
    }

    @Operation(summary = "POST() /meet/upper", description = "상위노출 업소 등록")
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디", example = "2358201983"),
            @Parameter(name = "placeId", description = "사업자 등록번호", example = "110242317"),
            @Parameter(name = "placeName", description = "업소 이름", example = "이디야커피"),
            @Parameter(name = "categoryId", description = "카테고리 아이디", example = "1"),
            @Parameter(name = "page", description = "업소 홈페이지", example = "http://naver.com"),
            @Parameter(name = "crn", description = "사업자 등록증 이미지 파일", example = "crn.jpg"),
            @Parameter(name = "sido", description = "시/도", example = "서울특별시"),
            @Parameter(name = "sigungu", description = "시/군/구", example = "강남구"),
            @Parameter(name = "dong", description = "동/읍/면", example = "삼성동"),
            @Parameter(name = "period", description = "상위 노출 기간(일)", example = "30 / 90 / 180"),
            @Parameter(name = "money", description = "가격", example = "20000 / 55000 / 10000")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "상위노출 업소 등록 성공"),
            @ApiResponse(responseCode = "400", description = "상위노출 업소 등록 실패")
    })
    @PostMapping("/upper")
    public ResponseEntity<?> addUpperPlace(@ModelAttribute MoimPlaceUpperDto moimPlaceUpperDto) {
        try {
            moimUpperService.addUpperPlace(moimPlaceUpperDto);
            return ResponseEntity.ok().body("ok");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("등록 실패");
        }
    }

    @Operation(summary = "GET() /meet/upper/{userId}", description = "상위노출 업소 처리 상태 조회")
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디", example = "110242317")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "상위노출 업소 처리 상태 조회 성공", content = @Content(schema = @Schema(implementation = MoimPlaceUpper.class)))
    })
    @GetMapping("/upper/{userId}")
    public ResponseEntity<?> getUpperPlace(@PathVariable("userId") long userId) {
        return ResponseEntity.ok().body(moimUpperService.getUpperPlace(userId));
    }

    @Operation(summary = "POST() /meet/upper/history", description = "상위노출 업소 히스토리 등록")
    @Parameters({
            @Parameter(name = "placeUpperId", description = "상위노출 업소 아이디", example = "1")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "상위노출 업소 히스토리 등록 성공")
    })
    @PostMapping("/upper/history")
    public ResponseEntity<?> addUpperPlaceHistory(@RequestBody PlaceUpperHistoryDto placeUpperHistoryDto) {
        moimUpperService.addUpperPlaceHistory(placeUpperHistoryDto);
        return ResponseEntity.ok().body("ok");
    }
}
