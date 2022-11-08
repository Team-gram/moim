package ajou.gram.moim.util.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JsonWebToken {

    private String token;
}
