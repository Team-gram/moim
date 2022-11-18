package ajou.gram.moim.repository;

import ajou.gram.moim.domain.UserIrregularSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserIrregularScheduleRepository extends JpaRepository<UserIrregularSchedule, Long> {
    List<UserIrregularSchedule> findByUserId(long userId);
    Optional<UserIrregularSchedule> findByUserIdAndId(long userId, long id);
    void deleteByUserIdAndId(long userId, long id);
}
