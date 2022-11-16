package ajou.gram.moim.repository;

import ajou.gram.moim.domain.Moim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MoimRepository extends JpaRepository<Moim, Long> {
    Moim save(Moim moim);
}
