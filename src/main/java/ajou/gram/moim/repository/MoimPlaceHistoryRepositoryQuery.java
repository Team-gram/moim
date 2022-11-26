package ajou.gram.moim.repository;

import ajou.gram.moim.dto.MoimMemberScheduleDto;
import ajou.gram.moim.dto.RecommendPlaceDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class MoimPlaceHistoryRepositoryQuery {

    private final DataSource dataSource;

    public List<RecommendPlaceDto> getRecommendPlaces(String sido, String sigungu, String categoryGroupName) throws SQLException {
        String query =
                "select address_id, place_name, category_group_name, sido, sigungu, dong " +
                "from moim_place_history " +
                "where category_group_name = ? " +
                "and sido = ? " +
                "and sigungu = ? " +
                "group by address_id, place_name, category_group_name, sido, sigungu, dong " +
                "order by count(address_id) desc";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<RecommendPlaceDto> recommendPlaceDtos = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, categoryGroupName);
            pstmt.setString(2, sido);
            pstmt.setString(3, sigungu);
            rs = pstmt.executeQuery();

            recommendPlaceDtos = new ArrayList<>();
            while(rs.next()) {
                RecommendPlaceDto recommendPlaceDto = new RecommendPlaceDto();
                recommendPlaceDto.setAddressId(rs.getLong("address_id"));
                recommendPlaceDto.setPlaceName(rs.getString("place_name"));
                recommendPlaceDto.setCategoryGroupName(rs.getString("category_group_name"));
                recommendPlaceDto.setSido(rs.getString("sido"));
                recommendPlaceDto.setSigungu(rs.getString("sigungu"));
                recommendPlaceDto.setDong(rs.getString("dong"));
                recommendPlaceDtos.add(recommendPlaceDto);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            if (conn != null) conn.close();
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }

        return recommendPlaceDtos;
    }
}
