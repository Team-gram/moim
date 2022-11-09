package ajou.gram.moim.service;

import ajou.gram.moim.domain.UserMessage;
import ajou.gram.moim.repository.UserMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserMessageService {

    private final UserMessageRepository userMessageRepository;

    public List<UserMessage> getMessages(long id) {
        return userMessageRepository.findByUserId(id);
    }
}
