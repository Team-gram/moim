package ajou.gram.moim.service;

import ajou.gram.moim.domain.*;
import ajou.gram.moim.dto.JoinDto;
import ajou.gram.moim.dto.KakaoDto;
import ajou.gram.moim.repository.*;
import ajou.gram.moim.util.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserCategoryRepository userCategoryRepository;
    private final UserMessageRepository userMessageRepository;
    private final UserRegularScheduleRepository userRegularScheduleRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> getUser(long id) {
        return userRepository.findById(id);
    }

    public void addUser(KakaoDto kakaoDto) {
        User user = new User(
                kakaoDto.getId(), kakaoDto.getNickName(),
                kakaoDto.getProfileImage(),
                null, null, null,
                null,
                null,
                null, "N",
                null, null, null,
                (short) 0, "USER", passwordEncoder.encode(String.valueOf(kakaoDto.getId()))
        );
        userRepository.save(user);
    }
    public void addUser(JoinDto joinDto) throws ParseException {
        String from = joinDto.getBirthday();
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        Date to = fm.parse(from);

        User user = new User(
                joinDto.getId(), joinDto.getName(),
                joinDto.getProfileImage(),
                joinDto.getSido(), joinDto.getSigungu(), joinDto.getDong(),
                joinDto.getGender(),
                to,
                joinDto.getDetail(), joinDto.getIsPublish(),
                new Date(), null, null,
                (short) 0, "USER", null
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

    public String login(String id) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id, null);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        String token = jwtTokenProvider.createToken(authentication);
        return token;
    }

    public List<UserMessage> getMessages(long id) {
        return userMessageRepository.findByToId(id);
    }

    public List<UserRegularSchedule> getUserRegularSchedule(long userId) {
        return userRegularScheduleRepository.findByUserId(userId);
    }

    public void addUserRegularSchedule(UserRegularSchedule userRegularSchedule) {
        userRegularScheduleRepository.save(userRegularSchedule);
    }
}
