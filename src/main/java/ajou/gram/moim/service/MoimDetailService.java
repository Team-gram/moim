package ajou.gram.moim.service;

import ajou.gram.moim.domain.MoimRegularSchedule;
import ajou.gram.moim.repository.MoimRegularScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MoimDetailService {

    private final MoimRegularScheduleRepository moimRegularScheduleRepository;

    public List<MoimRegularSchedule> getMoimRegularSchedule(long moimId) {
        return moimRegularScheduleRepository.findByMoimId(moimId);
    }
}
