package ajou.gram.moim.service;

import ajou.gram.moim.domain.Moim;
import ajou.gram.moim.domain.MoimMember;
import ajou.gram.moim.dto.JoinMoimDto;
import ajou.gram.moim.repository.MoimMemberRepository;
import ajou.gram.moim.repository.MoimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Transactional
@RequiredArgsConstructor
public class MoimService {

    private final MoimRepository moimRepository;
    private final MoimMemberRepository moimMemberRepository;

    public List<Moim> getMoims(int categoryId) {
        return moimRepository.findByCategoryId(categoryId);
    }

    public Optional<Moim> getMoim(long id) {
        return moimRepository.findById(id);
    }

    public void addMoim(Moim moim) {
        moim.setCreateDate(new Date());
        moim.setMoimLevel((short) 0);
        moimRepository.save(moim);
    }

    public List<Moim> getMoimsByTitle(String title) {
        return moimRepository.findByTitleLike("%" + title + "%");
    }

    public void moimJoin(MoimMember moimMember) {
        moimMember.setRegisterDate(new Date());
        moimMember.setLevel((short) 2);
        moimMemberRepository.save(moimMember);
    }

    public void moimJoinMessage(JoinMoimDto joinMoimDto) {
        Optional<Moim> moim = moimRepository.findById(joinMoimDto.getMoimId());
        AtomicLong moimOwner = new AtomicLong(0L);
        moim.ifPresent(selectedMoim -> {
            moimOwner.set(selectedMoim.getUserId());
        });
        // 모임 방장에게 메세지 전송하는 로직
    }
}
