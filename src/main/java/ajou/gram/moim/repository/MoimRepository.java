package ajou.gram.moim.repository;

import ajou.gram.moim.domain.Moim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoimRepository extends JpaRepository<Moim, Long> {
    List<Moim> findByCategoryId(int categoryId);
    List<Moim> findByTitleLike(String title);
}
