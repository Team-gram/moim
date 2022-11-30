package ajou.gram.moim.repository;

import ajou.gram.moim.dto.MoimScheduleMemberDto;
import ajou.gram.moim.dto.MoimScheduleReferenceDto;
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
public class MoimScheduleReferenceRepositoryQuery {

    private final DataSource dataSource;

    public List<MoimScheduleReferenceDto> getMoimScheduleReferences(long moimId, long moimScheduleId) throws SQLException {
        String query =
                "select id, moim_id, moim_schedule_id, user_id, name, (select name from user where user_id = id) user_name, status " +
                        "from moim_schedule_reference " +
                        "where moim_id = ? " +
                        "and moim_schedule_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MoimScheduleReferenceDto> moimScheduleReferenceDtos = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setLong(1, moimId);
            pstmt.setLong(2, moimScheduleId);
            rs = pstmt.executeQuery();

            moimScheduleReferenceDtos = new ArrayList<>();
            while(rs.next()) {
                MoimScheduleReferenceDto moimScheduleReferenceDto = new MoimScheduleReferenceDto();
                moimScheduleReferenceDto.setId(rs.getLong("id"));
                moimScheduleReferenceDto.setMoimId(rs.getLong("moim_id"));
                moimScheduleReferenceDto.setMoimScheduleId(rs.getLong("moim_schedule_id"));
                moimScheduleReferenceDto.setUserId(rs.getLong("user_id"));
                moimScheduleReferenceDto.setName(rs.getString("name"));
                moimScheduleReferenceDto.setUserName(rs.getString("user_name"));
                moimScheduleReferenceDto.setStatus(rs.getString("status"));
                moimScheduleReferenceDtos.add(moimScheduleReferenceDto);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            if (conn != null) conn.close();
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }

        return moimScheduleReferenceDtos;
    }
}
