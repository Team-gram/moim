package ajou.gram.moim.repository;

import ajou.gram.moim.domain.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMessageRepository extends JpaRepository<UserMessage, Long> {
    List<UserMessage> findByToId(long toId);
    UserMessage findByMoimIdAndToIdAndType(long moimId, long toId, String type);

    List<UserMessage> findByToIdAndStatus(long id, short status);
}
