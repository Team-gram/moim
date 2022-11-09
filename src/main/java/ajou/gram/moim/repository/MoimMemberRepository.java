package ajou.gram.moim.repository;

import ajou.gram.moim.domain.MoimMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoimMemberRepository extends JpaRepository<MoimMember, Long> {
}
