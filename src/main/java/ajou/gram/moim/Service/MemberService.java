package ajou.gram.moim.Service;

import ajou.gram.moim.domain.Member;
import ajou.gram.moim.repository.JdbcMemberRepository;
import ajou.gram.moim.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final JdbcMemberRepository jdbcMemberRepository;
    @Autowired
    public MemberService(MemberRepository memberRepository, JdbcMemberRepository jdbcMemberRepository) {
        this.memberRepository = memberRepository;
        this.jdbcMemberRepository = jdbcMemberRepository;
    }

    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    public List<Member> findMembers() {
        return jdbcMemberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("name is dupilcated");
                        });
    }
}
