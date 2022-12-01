package ajou.gram.moim.repository;

import ajou.gram.moim.dto.MoimMemberScheduleDto;
import ajou.gram.moim.dto.MoimUpperDto;
import ajou.gram.moim.dto.MoimUpperPrintDto;
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
public class MoimUpperRepositoryQuery {

    private final DataSource dataSource;

    public List<MoimUpperPrintDto> getUpperMoims(int categoryId) throws SQLException {
        String query = "select count(m.id) count, m.id, m.user_id, m.category_id, m.title, m.content, m.sido, m.sigungu, m.dong, m.isPublish, m.isFreeEnter, m.maxMember, m.createDate, m.moimLevel, m.thumbnail " +
                "from moim m " +
                "inner join moim_upper u on m.id = u.moim_id " +
                "inner join moim_upper_history h on m.id = h.moim_id " +
                "where m.category_id = ? " +
                "and u.recStartDate < current_timestamp() " +
                "and current_timestamp() < u.recEndDate " +
                "group by m.id " +
                "order by count asc " +
                "limit 5";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MoimUpperPrintDto> moimUpperPrintDtos = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, categoryId);
            rs = pstmt.executeQuery();

            moimUpperPrintDtos = new ArrayList<>();
            while(rs.next()) {
                MoimUpperPrintDto moimUpperPrintDto = new MoimUpperPrintDto();
                moimUpperPrintDto.setCount(rs.getInt("count"));
                moimUpperPrintDto.setId(rs.getLong("id"));
                moimUpperPrintDto.setUserId(rs.getLong("user_id"));
                moimUpperPrintDto.setCategoryId(rs.getInt("category_id"));
                moimUpperPrintDto.setTitle(rs.getString("title"));
                moimUpperPrintDto.setContent(rs.getString("content"));
                moimUpperPrintDto.setSido(rs.getString("sido"));
                moimUpperPrintDto.setSigungu(rs.getString("sigungu"));
                moimUpperPrintDto.setDong(rs.getString("dong"));
                moimUpperPrintDto.setIsPublish(rs.getString("isPublish"));
                moimUpperPrintDto.setIsFreeEnter(rs.getString("isFreeEnter"));
                moimUpperPrintDto.setMaxMember(rs.getInt("maxMember"));
                moimUpperPrintDto.setCreateDate(rs.getDate("createDate"));
                moimUpperPrintDto.setMoimLevel(rs.getInt("moimLevel"));
                moimUpperPrintDto.setThumbnail(rs.getString("thumbnail"));
                moimUpperPrintDtos.add(moimUpperPrintDto);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            if (conn != null) conn.close();
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }

        return moimUpperPrintDtos;
    }

    public int checkLimitUpperMoim(MoimUpperDto moimUpperDto) throws SQLException {
        String query = "select count(*) count " +
                        "from moim_upper u " +
                        "inner join moim m on u.moim_id = m.id " +
                        "where m.category_id = (select category_id from moim where id = ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setLong(1, moimUpperDto.getMoimId());
            rs = pstmt.executeQuery();

            while(rs.next()) {
                count = rs.getInt("count");
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            if (conn != null) conn.close();
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }

        return count;
    }
}
