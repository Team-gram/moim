package ajou.gram.moim.repository;

import ajou.gram.moim.domain.MoimMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MoimMemberRepository extends JpaRepository<MoimMember, Long> {

    List<MoimMember> findByUserId(long userId);
    MoimMember findOneByUserId(long userId);
    List<MoimMember> findByMoimId(long moimId);
    void deleteByMoimIdAndUserId(long moimId, long userId);
    Optional<MoimMember> findByMoimIdAndUserId(long moimId, long userId);
}
