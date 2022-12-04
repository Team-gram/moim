package ajou.gram.moim.service;

import ajou.gram.moim.domain.*;
import ajou.gram.moim.dto.AcceptDto;
import ajou.gram.moim.dto.CreateRegularScheduleDto;
import ajou.gram.moim.dto.JoinDto;
import ajou.gram.moim.dto.KakaoDto;
import ajou.gram.moim.repository.*;
import ajou.gram.moim.util.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
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
    private final UserRegularScheduleRepository userRegularScheduleRepository;
    private final UserRegularScheduleRepositoryQuery userRegularScheduleRepositoryQuery;
    private final UserIrregularScheduleRepository userIrregularScheduleRepository;
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
        Optional<User> user = userRepository.findById(joinDto.getId());
        user.ifPresent(selectedUser -> {
            selectedUser.setName(joinDto.getName());
            selectedUser.setSido(joinDto.getSido());
            selectedUser.setSigungu(joinDto.getSigungu());
            selectedUser.setDong(joinDto.getDong());
            selectedUser.setGender(joinDto.getGender());
            selectedUser.setBirthday(to);
            selectedUser.setDetail(joinDto.getDetail());
            selectedUser.setIsPublish(joinDto.getIsPublish());
            userRepository.save(selectedUser);
        });

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
        return userMessageRepository.findByToIdAndStatus(id, (short) 0);
    }

    public void setMesageStatus(AcceptDto acceptDto) {
        Optional<UserMessage> userMessage = userMessageRepository.findById(acceptDto.getMessageId());
        userMessage.ifPresent(m -> {
            m.setStatus((short) 1);
            userMessageRepository.save(m);
        });
    }

    public List<UserRegularSchedule> getUserRegularSchedule(long userId) {
        return userRegularScheduleRepository.findByUserId(userId);
    }

    public List<UserIrregularSchedule> getUserIrregularSchedule(long userId) {
        return userIrregularScheduleRepository.findByUserId(userId);
    }

    public boolean validateShedule(long userId, long scheduleId,CreateRegularScheduleDto createRegularScheduleDto) {
        return userRegularScheduleRepositoryQuery.validateSchedule(userId, scheduleId, createRegularScheduleDto);
    }

    public void addUserRegularSchedule(CreateRegularScheduleDto createRegularScheduleDto) {
        LocalTime startTime = LocalTime.parse(timeParse(createRegularScheduleDto.getStartTime()));
        LocalTime endTime = LocalTime.parse(timeParse(createRegularScheduleDto.getEndTime()));
        UserRegularSchedule userRegularSchedule = new UserRegularSchedule();
        userRegularSchedule.setUserId(createRegularScheduleDto.getUserId());
        userRegularSchedule.setDay(createRegularScheduleDto.getDay());
        userRegularSchedule.setStartTime(startTime);
        userRegularSchedule.setEndTime(endTime);
        userRegularSchedule.setTitle(createRegularScheduleDto.getTitle());
        userRegularSchedule.setDetail(createRegularScheduleDto.getDetail());
        userRegularScheduleRepository.save(userRegularSchedule);
    }

    public void addUserIrregularSchedule(UserIrregularSchedule userIrregularSchedule) {
        userIrregularScheduleRepository.save(userIrregularSchedule);
    }

    public void updateUserRegularSchedule(long userId, long scheduleId, CreateRegularScheduleDto createRegularScheduleDto) {
        LocalTime startTime = LocalTime.parse(timeParse(createRegularScheduleDto.getStartTime()));
        LocalTime endTime = LocalTime.parse(timeParse(createRegularScheduleDto.getEndTime()));
        Optional<UserRegularSchedule> optionalUserRegularSchedule = userRegularScheduleRepository.findById(scheduleId);
        optionalUserRegularSchedule.ifPresent(m -> {
            m.setDay(createRegularScheduleDto.getDay());
            m.setStartTime(startTime);
            m.setEndTime(endTime);
            if(m.getTitle() != null) m.setTitle(createRegularScheduleDto.getTitle());
            if(m.getDetail() != null) m.setDetail(createRegularScheduleDto.getDetail());
            userRegularScheduleRepository.save(m);
        });
    }

    public void updateUserIrregularSchedule(long userId, long scheduleId, UserIrregularSchedule userIrregularSchedule) {
        Optional<UserIrregularSchedule> optionalUserIrregularSchedule = userIrregularScheduleRepository.findById(scheduleId);
        optionalUserIrregularSchedule.ifPresent(m -> {
            m.setDate(userIrregularSchedule.getDate());
            m.setStartTime(userIrregularSchedule.getStartTime());
            m.setEndTime(userIrregularSchedule.getEndTime());
            if (userIrregularSchedule.getTitle() != null) m.setTitle(userIrregularSchedule.getTitle());
            if (userIrregularSchedule.getDetail() != null) m.setDetail(userIrregularSchedule.getDetail());
            userIrregularScheduleRepository.save(m);
        });
    }

    public void deleteUserRegularSchedule(long userId, long scheduleId) {
        userRegularScheduleRepository.deleteByUserIdAndId(userId, scheduleId);
    }


    public void deleteUserIrregularSchedule(long userId, long scheduleId) {
        userIrregularScheduleRepository.deleteByUserIdAndId(userId, scheduleId);
    }

    public Optional<UserRegularSchedule> getUserRegularScheduleDetail(long userId, long scheduleId) {
        return userRegularScheduleRepository.findByUserIdAndId(userId, scheduleId);
    }

    public Optional<UserIrregularSchedule> getUserIrregularScheduleDetail(long userId, long scheduleId) {
        return userIrregularScheduleRepository.findByUserIdAndId(userId, scheduleId);
    }

    public static String timeParse(String time) {
        String hour = time.split(":")[0];
        String minute = time.split(":")[1];
        if (hour.length() == 1) {
            hour = "0" + hour;
        }
        if (minute.length() == 1) {
            minute = "0" + minute;
        }
        return hour + ":" + minute;
    }
}
