package ajou.gram.moim.controller;

import ajou.gram.moim.domain.Moim;
import ajou.gram.moim.dto.PlaceHistoryDto;
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

import java.util.List;

@Tag(name = "place", description = "모임 장소 관련 API")
@RestController
@RequestMapping("/place")
@RequiredArgsConstructor
public class PlaceController {

    private final MoimPlaceService moimPlaceService;

    @Operation(summary = "POST() /place/history", description = "장소 검색 이력 등록")
    @Parameters({
            @Parameter(name = "addressId", description = "장소 아이디", example = "1357915152"),
            @Parameter(name = "placeName", description = "장소 이름", example = "아주대학교"),
            @Parameter(name = "categoryGroupName", description = "장소 카테고리", example = "음식점"),
            @Parameter(name = "sido", description = "시/도", example = "서울특별시"),
            @Parameter(name = "sigungu", description = "시/군/구", example = "강남구"),
            @Parameter(name = "dong", description = "동/읍/면", example = "삼성동")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "모임 조회 성공")
    })
    @PostMapping("/history")
    public ResponseEntity<?> addPlaceHistory(@RequestBody PlaceHistoryDto placeHistoryDto) {
        moimPlaceService.addPlaceHistory(placeHistoryDto);
        return ResponseEntity.ok().body("ok");
    }
}
