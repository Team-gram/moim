package ajou.gram.moim.repository;

import ajou.gram.moim.domain.MoimPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoimPlaceRepository extends JpaRepository<MoimPlace, Long> {
    List<MoimPlace> findByMoimIdAndScheduleId(long moimId, long scheduleId);
}
