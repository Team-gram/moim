package ajou.gram.moim.repository;

import ajou.gram.moim.domain.EmptyClass;

import java.util.*;

public class EmptyMemberRepository implements MemberRepository {

    private static Map<Long, EmptyClass> storage = new HashMap<Long, EmptyClass>();
    private static long sequence = 0L;

    @Override
    public EmptyClass save(EmptyClass empty) {
        empty.setId(++sequence);
        storage.put(empty.getId(), empty);
        return empty;
    }

    @Override
    public Optional<EmptyClass> findById(long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Optional<EmptyClass> findByName(String name) {
        return storage.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<EmptyClass> findAll() {
        return new ArrayList<>(storage.values());
    }
}
