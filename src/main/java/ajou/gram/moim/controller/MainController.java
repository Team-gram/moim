package ajou.gram.moim.controller;

import ajou.gram.moim.util.enumeration.EnumContract;
import ajou.gram.moim.util.enumeration.EnumMapper;
import ajou.gram.moim.util.enumeration.EnumValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    private final EnumMapper enumMapper;

    @Autowired
    public MainController(EnumMapper enumMapper) {
        this.enumMapper = enumMapper;
    }
    @GetMapping("/enum")
    public Map<String, List<EnumValue>> getEnum() {
        Map<String, List<EnumValue>> enumValues = new LinkedHashMap<>();
        enumValues.put("joinStatus", enumMapper.toEnumValues(EnumContract.JoinStatus.class));
        return enumValues;
    }
}
