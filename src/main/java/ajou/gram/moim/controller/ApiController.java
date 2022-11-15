package ajou.gram.moim.controller;

import ajou.gram.moim.util.enumeration.EnumContract;
import ajou.gram.moim.util.enumeration.EnumMapper;
import ajou.gram.moim.util.enumeration.EnumValue;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "api", description = "주소 데이터와 같은 리스트를 제공하는 API (미구현)")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final EnumMapper enumMapper;
    @Operation(summary = "GET() /api/enum", description = "미구현")
    @GetMapping("/enum")
    public Map<String, List<EnumValue>> getEnum() {
        Map<String, List<EnumValue>> enumValues = new LinkedHashMap<>();
        enumValues.put("joinStatus", enumMapper.toEnumValues(EnumContract.JoinStatus.class));
        return enumValues;
    }
}
