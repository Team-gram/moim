package ajou.gram.moim;

import ajou.gram.moim.domain.MoimScheduleMember;
import ajou.gram.moim.repository.*;
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
    private final UserMessageRepository userMessageRepository;
    private final UserRegularScheduleRepository userRegularScheduleRepository;
    private final UserIrregularScheduleRepository userIrregularScheduleRepository;

    private final CategoryRepository categoryRepository;

    private final MoimRepository moimRepository;
    private final MoimPlaceUpperRepository moimPlaceUpperRepository;
    private final MoimUpperRepository moimUpperRepository;
    private final MoimUpperHistoryRepository moimUpperHistoryRepository;
    private final MoimMemberRepository moimMemberRepository;
    private final MoimChatRepository moimChatRepository;
    private final MoimPlaceHistoryRepository moimPlaceHistoryRepository;
    private final MoimRegularScheduleRepository moimRegularScheduleRepository;
    private final MoimScheduleMemberRepository moimScheduleMemberRepository;
    private final MoimScheduleReferenceRepository moimScheduleReferenceRepository;

    @Bean
    public QuantitativeKPI quantitativeKPI() {
        return new QuantitativeKPI();
    }
}
