package ajou.gram.moim.controller;

import ajou.gram.moim.domain.MoimRegularSchedule;
import ajou.gram.moim.dto.MoimMemberScheduleDto;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
