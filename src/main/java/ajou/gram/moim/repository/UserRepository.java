package ajou.gram.moim.repository;

import ajou.gram.moim.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
