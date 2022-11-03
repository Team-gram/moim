package ajou.gram.moim;

import ajou.gram.moim.util.aop.QuantitativeKPI;
import ajou.gram.moim.util.enumeration.EnumContract;
import ajou.gram.moim.util.enumeration.EnumMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

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
