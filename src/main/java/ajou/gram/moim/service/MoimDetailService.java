package ajou.gram.moim.service;

import ajou.gram.moim.domain.Moim;
import ajou.gram.moim.domain.MoimRegularSchedule;
import ajou.gram.moim.domain.MoimScheduleMember;
import ajou.gram.moim.domain.MoimScheduleReference;
import ajou.gram.moim.dto.*;
import ajou.gram.moim.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static ajou.gram.moim.service.UserService.timeParse;

@Service
@Transactional
@RequiredArgsConstructor
public class MoimDetailService {

    private final MoimRegularScheduleRepository moimRegularScheduleRepository;
    private final MoimScheduleRepositoryQuery moimScheduleRepositoryQuery;
    private final MoimScheduleMemberRepository moimScheduleMemberRepository;
    private final MoimScheduleMemberRepositoryQuery moimScheduleMemberRepositoryQuery;

    private final MoimScheduleReferenceRepository moimScheduleReferenceRepository;
    private final MoimScheduleReferenceRepositoryQuery moimScheduleReferenceRepositoryQuery;
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

    public List<MoimScheduleMemberDto> getMoimUsers(long moimId, long moimScheduleId) throws SQLException {
        return moimScheduleMemberRepositoryQuery.getMoimScheduleMembers(moimId, moimScheduleId);
    }

    public MoimScheduleMember moimScheduleJoin(JoinMoimScheduleDto joinMoimScheduleDto) {
        MoimScheduleMember moimScheduleMember = new MoimScheduleMember(joinMoimScheduleDto.getMoimId(), joinMoimScheduleDto.getMoimScheduleId(), joinMoimScheduleDto.getUserId());
        return moimScheduleMemberRepository.save(moimScheduleMember);
    }

    public void moimScheduleCancle(JoinMoimScheduleDto joinMoimScheduleDto) {
        moimScheduleMemberRepository.deleteByMoimIdAndMoimScheduleIdAndUserId(joinMoimScheduleDto.getMoimId(), joinMoimScheduleDto.getMoimScheduleId(), joinMoimScheduleDto.getUserId());
    }

    public List<MoimScheduleReferenceDto> getMoimScheduleReferences(long moimId, long moimScheduleId) throws SQLException {
        return moimScheduleReferenceRepositoryQuery.getMoimScheduleReferences(moimId, moimScheduleId);
    }

    public void addMoimScheduleReference(MoimScheduleReferenceDto moimScheduleReferenceDto) {
        MoimScheduleReference moimScheduleReference = new MoimScheduleReference();
        moimScheduleReference.setMoimId(moimScheduleReferenceDto.getMoimId());
        moimScheduleReference.setMoimScheduleId(moimScheduleReferenceDto.getMoimScheduleId());
        moimScheduleReference.setName(moimScheduleReferenceDto.getName());
        moimScheduleReference.setStatus("N");
        moimScheduleReferenceRepository.save(moimScheduleReference);
    }

    public void updateMoimScheduleReference(MoimScheduleReference moimScheduleReference) {
        Optional<MoimScheduleReference> moimScheduleReferenceOptional = moimScheduleReferenceRepository.findById(moimScheduleReference.getId());
        moimScheduleReferenceOptional.ifPresent(m -> {
            m.setStatus("Y");
            m.setUserId(moimScheduleReference.getUserId());
            moimScheduleReferenceRepository.save(m);
        });
    }

    public void deleteMoimScheduleReference(MoimScheduleReference moimScheduleReference) {
        moimScheduleReferenceRepository.deleteById(moimScheduleReference.getId());
    }
}
