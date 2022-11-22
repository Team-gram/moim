package ajou.gram.moim.repository;

import ajou.gram.moim.domain.MoimChat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoimChatRepository extends JpaRepository<MoimChat, Long> {
    List<MoimChat> findByMoimId(long moimId);
}
