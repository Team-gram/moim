package ajou.gram.moim.repository;

import ajou.gram.moim.dto.MoimScheduleMemberDto;
import ajou.gram.moim.dto.RecommendPlaceDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class MoimScheduleMemberRepositoryQuery {

    private final DataSource dataSource;

    public List<MoimScheduleMemberDto> getMoimScheduleMembers(long moimId, long moimScheduleId) throws SQLException {
        String query =
                "select moim_schedule_member.*, user.name name " +
                "from moim_schedule_member " +
                "inner join user on moim_schedule_member.user_id = user.id " +
                "where moim_id = ? " +
                "and moim_schedule_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MoimScheduleMemberDto> moimScheduleMemberDtos = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setLong(1, moimId);
            pstmt.setLong(2, moimScheduleId);
            rs = pstmt.executeQuery();

            moimScheduleMemberDtos = new ArrayList<>();
            while(rs.next()) {
                MoimScheduleMemberDto moimScheduleMemberDto = new MoimScheduleMemberDto();
                moimScheduleMemberDto.setMoimId(rs.getLong("moim_id"));
                moimScheduleMemberDto.setMoimScheduleId(rs.getLong("moim_schedule_id"));
                moimScheduleMemberDto.setUserId(rs.getLong("user_id"));
                moimScheduleMemberDto.setName(rs.getString("name"));
                moimScheduleMemberDtos.add(moimScheduleMemberDto);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            if (conn != null) conn.close();
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }

        return moimScheduleMemberDtos;
    }
}
