package ajou.gram.moim.repository;

import ajou.gram.moim.domain.EmptyClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmptyMemberRepositoryTest {

    MemberRepository repository = new EmptyMemberRepository();

    @Test
    public void save() {
        EmptyClass member = new EmptyClass();
        member.setName("spring");

        repository.save(member);
        EmptyClass result = repository.findById(member.getId()).get();
        Assertions.assertEquals(member, result);
    }
}
