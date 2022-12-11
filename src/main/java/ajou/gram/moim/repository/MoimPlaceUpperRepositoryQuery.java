package ajou.gram.moim.repository;

import ajou.gram.moim.domain.MoimPlaceUpper;
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
public class MoimPlaceUpperRepositoryQuery {

    private final DataSource dataSource;

    public List<MoimPlaceUpper> findByCategoryId(long categoryId) throws SQLException {
        String query =
                "select moim_place_upper.id, user_id, moim_place_upper.place_id, place_name, category_id, page, crn, sido, sigungu, dong, recStartDate, recEndDate, status " +
                "from moim_place_upper " +
                "inner join moim_place_upper_history on moim_place_upper.id = moim_place_upper_history.place_upper_id " +
                "where recStartDate < current_timestamp() " +
                "and current_timestamp() < recEndDate " +
                "and category_id = ? " +
                "group by moim_place_upper_history.place_upper_id " +
                "order by count(moim_place_upper_history.place_upper_id) asc " +
                "limit 5";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MoimPlaceUpper> moimPlaceUppers = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setLong(1, categoryId);
            rs = pstmt.executeQuery();

            moimPlaceUppers = new ArrayList<>();
            while(rs.next()) {
                MoimPlaceUpper moimPlaceUpper = new MoimPlaceUpper();
                moimPlaceUpper.setId(rs.getLong("id"));
                moimPlaceUpper.setUserId(rs.getLong("user_id"));
                moimPlaceUpper.setPlaceId(rs.getLong("place_id"));
                moimPlaceUpper.setPlaceName(rs.getString("place_name"));
                moimPlaceUpper.setCategoryId(rs.getInt("category_id"));
                moimPlaceUpper.setPage(rs.getString("page"));
                moimPlaceUpper.setCrn(rs.getString("crn"));
                moimPlaceUpper.setSido(rs.getString("sido"));
                moimPlaceUpper.setSigungu(rs.getString("sigungu"));
                moimPlaceUpper.setDong(rs.getString("dong"));
                moimPlaceUppers.add(moimPlaceUpper);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            if (conn != null) conn.close();
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }

        return moimPlaceUppers;
    }
}
