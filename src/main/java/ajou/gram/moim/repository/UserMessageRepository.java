package ajou.gram.moim.repository;

import ajou.gram.moim.domain.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMessageRepository extends JpaRepository<UserMessage, Long> {
}
