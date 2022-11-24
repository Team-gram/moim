package ajou.gram.moim.service;

import ajou.gram.moim.domain.MoimRegularSchedule;
import ajou.gram.moim.domain.User;
import ajou.gram.moim.domain.UserRegularSchedule;
import ajou.gram.moim.dto.CreateMoimRegularScheduleDto;
import ajou.gram.moim.dto.CreateRegularScheduleDto;
import ajou.gram.moim.dto.MoimMemberScheduleDto;
import ajou.gram.moim.repository.MoimRegularScheduleRepository;
import ajou.gram.moim.repository.MoimScheduleRepositoryQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

import static ajou.gram.moim.service.UserService.timeParse;

@Service
@Transactional
@RequiredArgsConstructor
public class MoimDetailService {

    private final MoimRegularScheduleRepository moimRegularScheduleRepository;
    private final MoimScheduleRepositoryQuery moimScheduleRepositoryQuery;

    public List<MoimMemberScheduleDto> getMoimUserSchedules(long moimId) throws SQLException {
        return moimScheduleRepositoryQuery.getMoimUserSchedules(moimId);
    }

    public List<MoimRegularSchedule> getMoimRegularSchedule(long moimId) {
        return moimRegularScheduleRepository.findByMoimId(moimId);
    }

    public void addMoimRegularSchedule(CreateMoimRegularScheduleDto createMoimRegularScheduleDto) {
        LocalTime startTime = LocalTime.parse(timeParse(createMoimRegularScheduleDto.getStartTime()));
        LocalTime endTime = LocalTime.parse(timeParse(createMoimRegularScheduleDto.getEndTime()));
        MoimRegularSchedule moimRegularSchedule = new MoimRegularSchedule();
        moimRegularSchedule.setMoimId(createMoimRegularScheduleDto.getMoimId());
        moimRegularSchedule.setDay(createMoimRegularScheduleDto.getDay());
        moimRegularSchedule.setStartTime(startTime);
        moimRegularSchedule.setEndTime(endTime);
        moimRegularSchedule.setScheduleName(createMoimRegularScheduleDto.getScheduleName());
        moimRegularSchedule.setScheduleDetail(createMoimRegularScheduleDto.getScheduleDetail());
        moimRegularScheduleRepository.save(moimRegularSchedule);
    }

    public void updateMoimRegularSchedule(long moimId, long scheduleId, CreateMoimRegularScheduleDto createMoimRegularScheduleDto) {
        LocalTime startTime = LocalTime.parse(timeParse(createMoimRegularScheduleDto.getStartTime()));
        LocalTime endTime = LocalTime.parse(timeParse(createMoimRegularScheduleDto.getEndTime()));
        MoimRegularSchedule moimRegularSchedule = new MoimRegularSchedule();
        moimRegularSchedule.setId(scheduleId);
        moimRegularSchedule.setMoimId(moimId);
        moimRegularSchedule.setDay(createMoimRegularScheduleDto.getDay());
        moimRegularSchedule.setStartTime(startTime);
        moimRegularSchedule.setEndTime(endTime);
        moimRegularSchedule.setScheduleName(createMoimRegularScheduleDto.getScheduleName());
        moimRegularSchedule.setScheduleDetail(createMoimRegularScheduleDto.getScheduleDetail());
        moimRegularScheduleRepository.save(moimRegularSchedule);
    }

    public void deleteMoimRegularSchedule(long moimId, long scheduleId) {
        moimRegularScheduleRepository.deleteByMoimIdAndId(moimId, scheduleId);
    }
}
