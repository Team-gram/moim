package ajou.gram.moim.repository;

import ajou.gram.moim.domain.MoimRegularSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoimRegularScheduleRepository extends JpaRepository<MoimRegularSchedule, Long> {
    List<MoimRegularSchedule> findByMoimId(long moimId);
    void deleteByMoimIdAndId(long moimId, long scheduleId);
}
