package ajou.gram.moim.controller;

import ajou.gram.moim.domain.MoimRegularSchedule;
import ajou.gram.moim.domain.MoimScheduleMember;
import ajou.gram.moim.dto.JoinMoimScheduleDto;
import ajou.gram.moim.dto.MoimMemberScheduleDto;
import ajou.gram.moim.dto.MoimScheduleMemberDto;
import ajou.gram.moim.service.MoimDetailService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Tag(name = "meet", description = "모임방 내부 API")
@RestController
@RequestMapping("/meet")
@RequiredArgsConstructor
public class MeetController {

    private final MoimDetailService moimDetailService;

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

    @Operation(summary = "GET() /meet//{moimId}/{scheduleId}", description = "모임 일정에 참가한 멤버 리스트 조회")
    @Parameters({
            @Parameter(name = "moimId", description = "모임 아이디(필수)", example = "1"),
            @Parameter(name = "moimScheduleId", description = "모임 일정 아이디(필수)", example = "1")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임 일정에 참가한 멤버 리스트 조회 성공", content = @Content(schema = @Schema(implementation = MoimScheduleMemberDto.class)))
    })
    @GetMapping("/{moimId}/{moimScheduleId}")
    public ResponseEntity<?> getMoimUserSchedules(@PathVariable("moimId") long moimId,
                                                  @PathVariable("moimScheduleId") long moimScheduleId) throws SQLException {
        return ResponseEntity.ok().body(moimDetailService.getMoimScheduleMembers(moimId, moimScheduleId));
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
}
