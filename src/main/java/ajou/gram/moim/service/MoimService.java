package ajou.gram.moim.service;

import ajou.gram.moim.domain.*;
import ajou.gram.moim.dto.CreateMoimDto;
import ajou.gram.moim.dto.JoinMoimDto;
import ajou.gram.moim.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MoimService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final UserCategoryRepository userCategoryRepository;
    private final MoimRepository moimRepository;
    private final MoimRepositoryQuery moimRepositoryQuery;
    private final MoimMemberRepository moimMemberRepository;
    private final UserMessageRepository userMessageRepository;

    public List<Moim> getMoims(int categoryId, String sido, String sigungu, String dong, String title) {
        return moimRepositoryQuery.getMoims(categoryId, sido, sigungu, dong, title);
    }

    public List<Optional<Moim>> getMoims(long userId) {
        List<MoimMember> moimMembers = moimMemberRepository.findByUserId(userId);
        List<Optional<Moim>> moims = new ArrayList<>();
        for (MoimMember m : moimMembers) {
            moims.add(moimRepository.findById(m.getMoimId()));
        }
        return moims;
    }

    public Optional<Moim> getMoim(long id) {
        return moimRepository.findById(id);
    }

    public void addMoim(CreateMoimDto createMoimDto) {
        Moim moim = new Moim();
        moim.setUserId(createMoimDto.getUserId());
        moim.setCategoryId(createMoimDto.getCategoryId());
        moim.setTitle(createMoimDto.getTitle());
        moim.setContent(createMoimDto.getContent());
        moim.setSido(createMoimDto.getSido());
        moim.setSigungu(createMoimDto.getSigungu());
        moim.setDong(createMoimDto.getDong());
        moim.setIsPublish(createMoimDto.getIsPublish());
        moim.setIsFreeEnter(createMoimDto.getIsFreeEnter());
        moim.setMaxMember(createMoimDto.getMaxMember());
        moim.setCreateDate(new Date());
        moim.setMoimLevel((short) 0);
        Moim newMoim = moimRepository.save(moim);

        MoimMember moimMember = new MoimMember();
        moimMember.setMoimId(newMoim.getId());
        moimMember.setUserId(newMoim.getUserId());
        moimMember.setRegisterDate(new Date());
        moimMember.setLevel((short) 0);
        moimMemberRepository.save(moimMember);
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

    public void recommendMoim(long userId) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(selectedUser -> {
            List<UserCategory> userCategories = userCategoryRepository.findByUserId(selectedUser.getId());
        });
    }
}
