package ajou.gram.moim.repository;

import ajou.gram.moim.domain.MoimPlaceUpper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoimPlaceUpperRepository extends JpaRepository<MoimPlaceUpper, Long> {
    List<MoimPlaceUpper> findByUserId(long userId);
}
