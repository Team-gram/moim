package ajou.gram.moim.service;

import ajou.gram.moim.domain.Moim;
import ajou.gram.moim.domain.MoimMember;
import ajou.gram.moim.domain.UserMessage;
import ajou.gram.moim.dto.JoinMoimDto;
import ajou.gram.moim.repository.MoimMemberRepository;
import ajou.gram.moim.repository.MoimRepository;
import ajou.gram.moim.repository.UserMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MoimService {

    private final MoimRepository moimRepository;
    private final MoimMemberRepository moimMemberRepository;
    private final UserMessageRepository userMessageRepository;

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
        moim.ifPresent(selectedMoim -> {
            UserMessage userMessage = new UserMessage();
            userMessage.setFromId(joinMoimDto.getUserId());
            userMessage.setToId(selectedMoim.getUserId());
            userMessage.setMoimId(joinMoimDto.getMoimId());
            userMessage.setType("JOIN");
            userMessage.setMessage(joinMoimDto.getMessage());
            userMessage.setStatus((short) 0);
            userMessageRepository.save(userMessage);
        });
    }
}
