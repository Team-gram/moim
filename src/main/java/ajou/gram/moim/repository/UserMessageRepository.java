package ajou.gram.moim.repository;

import ajou.gram.moim.domain.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMessageRepository extends JpaRepository<UserMessage, Long> {
    List<UserMessage> findByUserId(long toId);
}
