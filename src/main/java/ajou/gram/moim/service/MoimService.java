package ajou.gram.moim.service;

import ajou.gram.moim.domain.*;
import ajou.gram.moim.dto.ChatDto;
import ajou.gram.moim.dto.CreateMoimDto;
import ajou.gram.moim.dto.JoinMoimDto;
import ajou.gram.moim.repository.*;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MoimService {
    private final CategoryRepositoryQuery categoryRepositoryQuery;
    private final UserCategoryRepository userCategoryRepository;
    private final MoimRepository moimRepository;
    private final MoimRepositoryQuery moimRepositoryQuery;
    private final MoimMemberRepository moimMemberRepository;
    private final MoimChatRepository moimChatRepository;
    private final UserMessageRepository userMessageRepository;

    public List<Moim> getMoims(int categoryId, String sido, String sigungu, String dong, String title) {
        List<Category> categories = categoryRepositoryQuery.findByCategoryIdAtCategoryParentId(categoryId);
        return moimRepositoryQuery.getMoims(categoryId, sido, sigungu, dong, title, categories);
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
        moim.setThumbnail(null);
        Moim newMoim = moimRepository.save(moim);

        MoimMember moimMember = new MoimMember();
        moimMember.setMoimId(newMoim.getId());
        moimMember.setUserId(newMoim.getUserId());
        moimMember.setRegisterDate(new Date());
        moimMember.setLevel((short) 0);
        moimMemberRepository.save(moimMember);
    }

    public void updateMoim(Moim moim) {
        Optional<Moim> moimOptional = moimRepository.findById(moim.getId());
        moimOptional.ifPresent(m -> {
            if (moim.getCategoryId() != 0) m.setCategoryId(moim.getCategoryId());
            if (moim.getTitle() != null) m.setTitle(moim.getTitle());
            if (moim.getContent() != null) m.setContent(moim.getContent());
            if (moim.getSido() != null)m.setSido(moim.getSido());
            if (moim.getSigungu() != null) m.setSigungu(moim.getSigungu());
            if (moim.getDong() != null) m.setDong(moim.getDong());
            if (moim.getIsPublish() != null) m.setIsPublish(moim.getIsPublish());
            if (moim.getIsFreeEnter() != null) m.setIsFreeEnter(moim.getIsFreeEnter());
            if (moim.getMaxMember() != 0) m.setMaxMember(moim.getMaxMember());
            moimRepository.save(m);
        });
    }

    public void moimJoin(MoimMember moimMember) {
        moimMember.setRegisterDate(new Date());
        moimMember.setLevel((short) 2);
        moimMemberRepository.save(moimMember);
    }

    public UserMessage getMessageCheck(long moimId, long toId, String type) {
        return userMessageRepository.findByMoimIdAndToIdAndType(moimId, toId, type);
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

    public void moimInviteMessage(JoinMoimDto joinMoimDto) {
        Optional<Moim> moim = moimRepository.findById(joinMoimDto.getMoimId());
        moim.ifPresent(selectedMoim -> {
            UserMessage userMessage = new UserMessage();
            userMessage.setFromId(selectedMoim.getUserId());
            userMessage.setToId(joinMoimDto.getUserId());
            userMessage.setMoimId(joinMoimDto.getMoimId());
            userMessage.setType("INVITE");
            userMessage.setMessage(joinMoimDto.getMessage());
            userMessage.setStatus((short) 0);
            userMessageRepository.save(userMessage);
        });
    }

    public JSONObject recommendMoim(User user) throws SQLException {
        List<UserCategory> userCategories = userCategoryRepository.findByUserId(user.getId());
        return moimRepositoryQuery.getRecommendMoims(user, userCategories);
    }

    public List<MoimChat> getMoimChats(long moimId) {
        return moimChatRepository.findByMoimId(moimId);
    }

    public void addChat(ChatDto chatDto) {
        MoimChat moimChat = new MoimChat();
        moimChat.setMoimId(chatDto.getMoimId());
        moimChat.setContent(chatDto.getContent());
        moimChat.setUserId(chatDto.getUserId());
        moimChat.setDecoration(0);
        moimChat.setCreatedAt(new Date());
        moimChatRepository.save(moimChat);
    }

    public List<MoimMember> getMoimMembers(long moimId) {
        return moimMemberRepository.findByMoimId(moimId);
    }

    public void updateMemberLevel(MoimMember moimMember) {
        MoimMember m = moimMemberRepository.findOneByUserId(moimMember.getUserId());
        m.setLevel(moimMember.getLevel());
        moimMemberRepository.save(m);
    }

    public void banishMember(MoimMember moimMember) {
        moimMemberRepository.deleteByMoimIdAndUserId(moimMember.getMoimId(), moimMember.getUserId());
    }
}
