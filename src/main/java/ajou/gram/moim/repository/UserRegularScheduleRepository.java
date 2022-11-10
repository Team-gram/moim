package ajou.gram.moim.repository;

import ajou.gram.moim.domain.UserRegularSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRegularScheduleRepository extends JpaRepository<UserRegularSchedule, Long> {
    List<UserRegularSchedule> findByUserId(long userId);
}
