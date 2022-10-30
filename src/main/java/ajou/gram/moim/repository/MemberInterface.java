package ajou.gram.moim.repository;

import ajou.gram.moim.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberInterface {
    Member save(Member empty);
    Optional<Member> findById(long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
