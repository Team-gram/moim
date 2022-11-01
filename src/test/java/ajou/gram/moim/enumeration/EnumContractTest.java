package ajou.gram.moim.enumeration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnumContractTest {

    private final EnumMapper enumMapper = new EnumMapper();

    @Test
    public void getEnum() {
        Map<String, List<EnumValue>> enumValues = new LinkedHashMap<>();
        enumValues.put("joinStatus", enumMapper.toEnumValues(EnumContract.JoinStatus.class));
        assertTrue(!enumValues.isEmpty());
    }
}
