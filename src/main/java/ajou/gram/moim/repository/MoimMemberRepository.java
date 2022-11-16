package ajou.gram.moim.repository;

import ajou.gram.moim.domain.MoimMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoimMemberRepository extends JpaRepository<MoimMember, Long> {

    List<MoimMember> findByUserId(long userId);
}
