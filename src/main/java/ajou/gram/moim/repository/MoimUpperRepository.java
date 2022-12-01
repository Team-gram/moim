package ajou.gram.moim.repository;

import ajou.gram.moim.domain.MoimUpper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MoimUpperRepository extends JpaRepository<MoimUpper, Long> {
    Optional<MoimUpper> findByMoimId(long moimId);
}
