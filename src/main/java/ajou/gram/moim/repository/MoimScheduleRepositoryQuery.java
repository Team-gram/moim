package ajou.gram.moim.repository;

import ajou.gram.moim.domain.User;
import ajou.gram.moim.domain.UserCategory;
import ajou.gram.moim.dto.MoimMemberScheduleDto;
import ajou.gram.moim.dto.RecommendMoimDto;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class MoimScheduleRepositoryQuery {

    private final DataSource dataSource;

    public List<MoimMemberScheduleDto> getMoimUserSchedules(long moimId) throws SQLException {
        String query = "select moim.id moim_id, user.id user_id, user.name, day, startTime, endTime, user_regular_schedule.title scheduleName, user_regular_schedule.detail scheduleDetail " +
                        "from moim " +
                        "inner join moim_member on moim.id = moim_member.moim_id  " +
                        "inner join user_regular_schedule on moim_member.user_id = user_regular_schedule.user_id  " +
                        "inner join user on user.id = moim_member.user_id  " +
                        "where timestampdiff(minute, startTime, endTime) >= 30 " +
                        "and moim_id = ? " +
                        "and user_regular_schedule.isPublish = 'Y' " +
                        "order by day, startTime";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MoimMemberScheduleDto> moimMemberScheduleDtos = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setLong(1, moimId);
            rs = pstmt.executeQuery();

            moimMemberScheduleDtos = new ArrayList<>();
            while(rs.next()) {
                MoimMemberScheduleDto moimMemberScheduleDto = new MoimMemberScheduleDto();
                moimMemberScheduleDto.setMoimId(rs.getLong("moim_id"));
                moimMemberScheduleDto.setUserId(rs.getLong("user_id"));
                moimMemberScheduleDto.setName(rs.getString("name"));
                moimMemberScheduleDto.setDay(rs.getShort("day"));
                moimMemberScheduleDto.setStartTime(rs.getString("startTime"));
                moimMemberScheduleDto.setEndTime(rs.getString("endTime"));
                moimMemberScheduleDto.setScheduleName(rs.getString("scheduleName"));
                moimMemberScheduleDto.setScheduleDetail(rs.getString("scheduleDetail"));
                moimMemberScheduleDtos.add(moimMemberScheduleDto);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            if (conn != null) conn.close();
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }

        return moimMemberScheduleDtos;
    }
}
