package ajou.gram.moim.service;

import ajou.gram.moim.domain.User;
import ajou.gram.moim.domain.UserCategory;
import ajou.gram.moim.domain.UserMessage;
import ajou.gram.moim.dto.JoinDto;
import ajou.gram.moim.repository.UserCategoryRepository;
import ajou.gram.moim.repository.UserMessageRepository;
import ajou.gram.moim.repository.UserRepository;
import ajou.gram.moim.repository.UserRepositoryTemp;
import ajou.gram.moim.util.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserCategoryRepository userCategoryRepository;
    private final UserMessageRepository userMessageRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    public void addUser(JoinDto joinDto) throws ParseException {
        String from = joinDto.getBirthday();
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        Date to = fm.parse(from);

        User user = new User(
                joinDto.getId(), joinDto.getName(),
                joinDto.getProfileImage(), joinDto.getEmail(), joinDto.getPhone(),
                joinDto.getSido(), joinDto.getSigungu(), joinDto.getDong(),
                joinDto.getGender(),
                to,
                joinDto.getDetail(), joinDto.getIsPublish(),
                new Date(), null, null,
                (short) 0, "USER"
        );
        userRepository.save(user);

        for (int i=0; i<joinDto.getCategories().size(); i++) {
            UserCategory category = new UserCategory(joinDto.getId(), joinDto.getCategories().get(i), (short) 1);
            userCategoryRepository.save(category);
        }
    }
    public Optional<User> validateId(long id) {
        return userRepository.findById(id);
    }

    public String login(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        String token = jwtTokenProvider.createToken(authentication);
        return token;
    }

    public List<UserMessage> getMessages(long id) {
        return userMessageRepository.findByUserId(id);
    }
}
