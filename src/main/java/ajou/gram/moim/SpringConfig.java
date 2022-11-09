package ajou.gram.moim;

import ajou.gram.moim.repository.MoimRepository;
import ajou.gram.moim.repository.UserCategoryRepository;
import ajou.gram.moim.repository.UserMessageRepository;
import ajou.gram.moim.repository.UserRepository;
import ajou.gram.moim.service.UserService;
import ajou.gram.moim.util.aop.QuantitativeKPI;
import ajou.gram.moim.util.enumeration.EnumContract;
import ajou.gram.moim.util.enumeration.EnumMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {

    private final UserRepository userRepository;
    private final UserCategoryRepository userCategoryRepository;
    private final MoimRepository moimRepository;
    private final UserMessageRepository userMessageRepository;
    @Bean
    public EnumMapper enumMapper() {
        EnumMapper enumMapper = new EnumMapper();
        enumMapper.put("joinStatus", EnumContract.JoinStatus.class);
        return enumMapper;
    }

    @Bean
    public QuantitativeKPI quantitativeKPI() {
        return new QuantitativeKPI();
    }
}
