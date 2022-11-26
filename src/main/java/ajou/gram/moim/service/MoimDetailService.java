package ajou.gram.moim.service;

import ajou.gram.moim.domain.MoimRegularSchedule;
import ajou.gram.moim.domain.MoimScheduleMember;
import ajou.gram.moim.dto.*;
import ajou.gram.moim.repository.MoimRegularScheduleRepository;
import ajou.gram.moim.repository.MoimScheduleMemberRepository;
import ajou.gram.moim.repository.MoimScheduleMemberRepositoryQuery;
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
    private final MoimScheduleMemberRepository moimScheduleMemberRepository;
    private final MoimScheduleMemberRepositoryQuery moimScheduleMemberRepositoryQuery;

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

    public List<MoimScheduleMemberDto> getMoimScheduleMembers(long moimId, long moimScheduleId) throws SQLException {
        return moimScheduleMemberRepositoryQuery.getMoimScheduleMembers(moimId, moimScheduleId);
    }

    public MoimScheduleMember moimScheduleJoin(JoinMoimScheduleDto joinMoimScheduleDto) {
        MoimScheduleMember moimScheduleMember = new MoimScheduleMember(joinMoimScheduleDto.getMoimId(), joinMoimScheduleDto.getMoimScheduleId(), joinMoimScheduleDto.getUserId());
        return moimScheduleMemberRepository.save(moimScheduleMember);
    }
}
