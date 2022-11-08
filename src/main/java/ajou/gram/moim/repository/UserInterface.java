package ajou.gram.moim.repository;

import ajou.gram.moim.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserInterface extends JpaRepository<User, Long> {
    Optional<User> findById(long id);
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByEmail(String email);
}
