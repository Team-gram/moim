package ajou.gram.moim.repository;

import ajou.gram.moim.domain.EmptyClass;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    EmptyClass save(EmptyClass empty);
    Optional<EmptyClass> findById(long id);
    Optional<EmptyClass> findByName(String name);
    List<EmptyClass> findAll();
}
