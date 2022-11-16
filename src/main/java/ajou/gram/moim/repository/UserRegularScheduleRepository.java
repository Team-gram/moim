package ajou.gram.moim.repository;

import ajou.gram.moim.domain.UserRegularSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRegularScheduleRepository extends JpaRepository<UserRegularSchedule, Long> {
    List<UserRegularSchedule> findByUserId(long userId);
    Optional<UserRegularSchedule>  findByUserIdAndId(long userId, long id);
    void deleteByUserIdAndId(long userId, long id);
}
