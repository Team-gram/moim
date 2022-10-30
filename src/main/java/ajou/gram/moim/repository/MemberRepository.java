package ajou.gram.moim.repository;

import ajou.gram.moim.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberRepository implements MemberInterface {

    private static Map<Long, Member> storage = new HashMap<Long, Member>();
    private static long sequence = 0L;

    @Override
    public Member save(Member empty) {
        empty.setId(++sequence);
        storage.put(empty.getId(), empty);
        return empty;
    }

    @Override
    public Optional<Member> findById(long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return storage.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(storage.values());
    }
}
