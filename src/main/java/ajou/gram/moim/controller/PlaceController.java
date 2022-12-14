package ajou.gram.moim.controller;

import ajou.gram.moim.domain.Moim;
import ajou.gram.moim.domain.MoimPlace;
import ajou.gram.moim.dto.MoimPlaceDto;
import ajou.gram.moim.dto.PlaceHistoryDto;
import ajou.gram.moim.dto.RecommendPlaceDto;
import ajou.gram.moim.service.MoimPlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Tag(name = "place", description = "모임 장소 관련 API")
@RestController
@RequestMapping("/place")
@RequiredArgsConstructor
public class PlaceController {

    private final MoimPlaceService moimPlaceService;

    @Operation(summary = "POST() /place/history", description = "장소 검색 히스토리 등록")
    @Parameters({
            @Parameter(name = "addressId", description = "장소 아이디", example = "1357915152"),
            @Parameter(name = "placeName", description = "장소 이름", example = "아주대학교"),
            @Parameter(name = "categoryGroupName", description = "장소 카테고리", example = "음식점"),
            @Parameter(name = "sido", description = "시/도", example = "서울특별시"),
            @Parameter(name = "sigungu", description = "시/군/구", example = "강남구"),
            @Parameter(name = "dong", description = "동/읍/면", example = "삼성동")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "히스토리 등록 성공")
    })
    @PostMapping("/history")
    public ResponseEntity<?> addPlaceHistory(@RequestBody PlaceHistoryDto placeHistoryDto) {
        moimPlaceService.addPlaceHistory(placeHistoryDto);
        return ResponseEntity.ok().body("ok");
    }

    @Operation(summary = "GET() /place/{moimId}/{scheduleId}", description = "모임 일정 장소 조회")
    @Parameters({
            @Parameter(name = "moimId", description = "모임 아이디", example = "1"),
            @Parameter(name = "scheduleId", description = "모임 일정 아이디", example = "1")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임 일정 장소 조회 성공", content = @Content(schema = @Schema(implementation = MoimPlace.class)))
    })
    @GetMapping("/{moimId}/{scheduleId}")
    public ResponseEntity<?> getMoimPlaces(@PathVariable("moimId") long moimId,
                                                @PathVariable("scheduleId") long scheduleId) {
        return ResponseEntity.ok().body(moimPlaceService.getMoimPlaces(moimId, scheduleId));
    }

    @Operation(summary = "POST() /place", description = "모임 장소 등록")
    @Parameters({
            @Parameter(name = "moimId", description = "모임 아이디", example = "1"),
            @Parameter(name = "scheduleId", description = "모임 일정 아이디", example = "1"),
            @Parameter(name = "addressId", description = "장소 아이디", example = "1357912358"),
            @Parameter(name = "placeName", description = "장소 이름", example = "아주대학교"),
            @Parameter(name = "sido", description = "시/도", example = "서울특별시"),
            @Parameter(name = "sigungu", description = "시/군/구", example = "강남구"),
            @Parameter(name = "dong", description = "동/읍/면", example = "삼성동")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임 장소 등록 성공")
    })
    @PostMapping("")
    public ResponseEntity<?> addMoimPlace(@RequestBody MoimPlaceDto moimPlaceDto) {
        moimPlaceService.addMoimPlace(moimPlaceDto);
        return ResponseEntity.ok().body("ok");
    }

    @Operation(summary = "DELETE() /place/{placeId}", description = "모임 장소 삭제")
    @Parameters({
            @Parameter(name = "placeId", description = "모임 일정 장소 아이디", example = "1")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임 장소 삭제 성공")
    })
    @DeleteMapping("/{placeId}")
    public ResponseEntity<?> deleteMoimPlace(@PathVariable("placeId") long placeId) {
        moimPlaceService.deleteMoimPlace(placeId);
        return ResponseEntity.ok().body("ok");
    }

    @Operation(summary = "GET() /place/recommend/{sido}/{sigungu}", description = "추천 장소 조회")
    @Parameters({
            @Parameter(name = "sido", description = "시/도", example = "서울특별시"),
            @Parameter(name = "sigungu", description = "시/군/구", example = "강남구")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "추천 장소 조회 성공", content = @Content(schema = @Schema(implementation = RecommendPlaceDto.class)))
    })
    @GetMapping("/recommend/{sido}/{sigungu}")
    public ResponseEntity<?> getRecommendPlaces(@PathVariable("sido") String sido,
                                                @PathVariable("sigungu") String sigungu) throws SQLException {
        return ResponseEntity.ok().body(moimPlaceService.getRecommendPlaces(sido, sigungu));
    }
}
