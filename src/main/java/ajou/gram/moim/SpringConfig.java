package ajou.gram.moim;

import ajou.gram.moim.repository.UserCategoryRepository;
import ajou.gram.moim.repository.UserRepository;
import ajou.gram.moim.service.UserService;
import ajou.gram.moim.util.aop.QuantitativeKPI;
import ajou.gram.moim.util.enumeration.EnumContract;
import ajou.gram.moim.util.enumeration.EnumMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final UserRepository userRepository;
    private final UserCategoryRepository userCategoryRepository;
    public SpringConfig(UserRepository userRepository, UserCategoryRepository userCategoryRepository) {
        this.userRepository = userRepository;
        this.userCategoryRepository = userCategoryRepository;
    }

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
