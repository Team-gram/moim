package ajou.gram.moim.repository;

import ajou.gram.moim.domain.MoimScheduleMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoimScheduleMemberRepository extends JpaRepository<MoimScheduleMember, Long> {
    void deleteByMoimIdAndMoimScheduleIdAndUserId(long moimId, long moimScheduleId, long userId);
}
