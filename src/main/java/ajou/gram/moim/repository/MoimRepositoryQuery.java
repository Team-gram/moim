package ajou.gram.moim.repository;

import ajou.gram.moim.domain.Category;
import ajou.gram.moim.domain.Moim;
import ajou.gram.moim.domain.User;
import ajou.gram.moim.domain.UserCategory;
import ajou.gram.moim.dto.RecommendMoimDto;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class MoimRepositoryQuery {

    private final EntityManager em;
    private final DataSource dataSource;

    public List<Moim> getMoims(int categoryId, String sido, String sigungu, String dong, String title, List<Category> categories) {
        boolean f_parent = false;
        boolean f_sido = false;
        boolean f_sigungu = false;
        boolean f_dong = false;
        boolean f_title = false;
        List<Integer> categoryIds = new ArrayList<>();

        String query = "select m from moim m where 1=1 ";

        if (categories.size() > 0) {
            query += "and m.categoryId in (:categories) ";
            for (Category c : categories) {
                categoryIds.add(c.getCategoryId());
            }
            f_parent = true;
        } else {
            if (categoryId == 0) {
                query += "and m.categoryId >= :categoryId ";
            } else {
                query += "and m.categoryId = :categoryId ";
            }
        }

        if (sido != null) {
            query += "and m.sido = :sido ";
            f_sido = true;
        }
        if (sigungu != null) {
            query += "and m.sigungu = :sigungu ";
            f_sigungu = true;
        }
        if (dong != null) {
            query += "and m.dong = :dong ";
            f_dong = true;
        }
        if (title != null) {
            query += "and m.title like :title ";
            f_title = true;
        }
        TypedQuery<Moim> moims = em.createQuery(query, Moim.class).setMaxResults(1000);
        if (f_parent) {
            moims = moims.setParameter("categories", categoryIds);
        } else {
            moims = moims.setParameter("categoryId", categoryId);
        }
        if (f_sido) {
            moims = moims.setParameter("sido", sido);
        }
        if (f_sigungu) {
            moims = moims.setParameter("sigungu", sigungu);
        }
        if (f_dong) {
            moims = moims.setParameter("dong", dong);
        }
        if (f_title) {
            moims = moims.setParameter("title", "%" + title + "%");
        }

        return moims.getResultList();
    }

    public JSONObject getRecommendMoims(User user, List<UserCategory> userCategories) throws SQLException {
        JSONObject jsonObject = new JSONObject();

        String query = "select count(user.id) members, moim.id moim_id, category.category_parent_id, moim.category_id, moim.title, moim.content, moim.sido, moim.sigungu, " +
                        "moim.dong, moim.isFreeEnter, moim.maxMember, moim.createDate, moim.moimLevel " +
                        "from user user " +
                        "inner join moim_member member on user.id = member.user_id " +
                        "inner join moim moim on member.moim_id = moim.id " +
                        "inner join category category on moim.category_id = category.category_id " +
                        "where moim.isPublish = 'Y' " +
                        "and moim.sido = ? " +
                        "group by moim.id " +
                        "having abs(timestampdiff(year, avg(user.birthday), str_to_date(?, '%Y%m%d'))) <= 10 " +
                        "order by members desc";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(query);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            String birthday = simpleDateFormat.format(user.getBirthday());
            pstmt.setString(1, user.getSido());
            pstmt.setString(2, birthday);
            rs = pstmt.executeQuery();
            List<RecommendMoimDto> recommendMoimDtos = new ArrayList<>();
            while(rs.next()) {
                RecommendMoimDto recommendMoimDto = new RecommendMoimDto();
                recommendMoimDto.setMembers(rs.getInt("members"));
                recommendMoimDto.setMoimId(rs.getLong("moim_id"));
                recommendMoimDto.setCategoryParentId(rs.getInt("category_parent_id"));
                recommendMoimDto.setCategoryId(rs.getInt("category_id"));
                recommendMoimDto.setTitle(rs.getString("title"));
                recommendMoimDto.setContent(rs.getString("content"));
                recommendMoimDto.setSido(rs.getString("sido"));
                recommendMoimDto.setSigungu(rs.getString("sigungu"));
                recommendMoimDto.setDong(rs.getString("dong"));
                recommendMoimDto.setIsFreeEnter(rs.getString("isFreeEnter"));
                recommendMoimDto.setMaxMember(rs.getInt("maxMember"));
                recommendMoimDto.setCreateDate(rs.getDate("createDate"));
                recommendMoimDto.setMoimLevel(rs.getInt("moimLevel"));
                recommendMoimDtos.add(recommendMoimDto);
            }
            jsonObject.put("age", recommendMoimDtos);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            if (conn != null) conn.close();
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }


        query =
                "select count(user.id) members, moim.id moim_id, category.category_parent_id, moim.category_id, moim.title, moim.content, moim.sido, moim.sigungu, moim.dong, moim.isFreeEnter, moim.maxMember, moim.createDate, moim.moimLevel " +
                        "from user user " +
                        "inner join moim_member member on user.id = member.user_id " +
                        "inner join moim moim on member.moim_id = moim.id " +
                        "inner join category category on moim.category_id = category.category_id " +
                        "where moim.isPublish = 'Y' " +
                        "and moim.sido = ? " +
                        "and user.gender = ? " +
                        "group by moim.id " +
                        "order by members desc";


        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, user.getSido());
            pstmt.setString(2, user.getGender());
            rs = pstmt.executeQuery();
            List<RecommendMoimDto> recommendMoimDtos = new ArrayList<>();
            while(rs.next()) {
                RecommendMoimDto recommendMoimDto = new RecommendMoimDto();
                recommendMoimDto.setMembers(rs.getInt("members"));
                recommendMoimDto.setMoimId(rs.getLong("moim_id"));
                recommendMoimDto.setCategoryParentId(rs.getInt("category_parent_id"));
                recommendMoimDto.setCategoryId(rs.getInt("category_id"));
                recommendMoimDto.setTitle(rs.getString("title"));
                recommendMoimDto.setContent(rs.getString("content"));
                recommendMoimDto.setSido(rs.getString("sido"));
                recommendMoimDto.setSigungu(rs.getString("sigungu"));
                recommendMoimDto.setDong(rs.getString("dong"));
                recommendMoimDto.setIsFreeEnter(rs.getString("isFreeEnter"));
                recommendMoimDto.setMaxMember(rs.getInt("maxMember"));
                recommendMoimDto.setCreateDate(rs.getDate("createDate"));
                recommendMoimDto.setMoimLevel(rs.getInt("moimLevel"));
                recommendMoimDtos.add(recommendMoimDto);
            }
            jsonObject.put("gender", recommendMoimDtos);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            if (conn != null) conn.close();
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }

        int limit = 1;
        for (UserCategory category : userCategories) {
            query =
                    "select count(user.id) members, moim.id moim_id, category.category_parent_id, moim.category_id, moim.title, moim.content, moim.sido, moim.sigungu, moim.dong, moim.isFreeEnter, moim.maxMember, moim.createDate, moim.moimLevel " +
                            "from user user " +
                            "inner join moim_member member on user.id = member.user_id " +
                            "inner join moim moim on member.moim_id = moim.id " +
                            "inner join category category on moim.category_id = category.category_id " +
                            "where moim.isPublish = 'Y' " +
                            "and moim.sido = ? " +
                            "and moim.category_id = ? " +
                            "group by moim.id " +
                            "order by members desc";

            try {
                conn = dataSource.getConnection();
                pstmt = conn.prepareStatement(query);

                pstmt.setString(1, user.getSido());
                pstmt.setInt(2, category.getCategoryId());
                rs = pstmt.executeQuery();
                List<RecommendMoimDto> recommendMoimDtos = new ArrayList<>();
                while(rs.next()) {
                    RecommendMoimDto recommendMoimDto = new RecommendMoimDto();
                    recommendMoimDto.setMembers(rs.getInt("members"));
                    recommendMoimDto.setMoimId(rs.getLong("moim_id"));
                    recommendMoimDto.setCategoryParentId(rs.getInt("category_parent_id"));
                    recommendMoimDto.setCategoryId(rs.getInt("category_id"));
                    recommendMoimDto.setTitle(rs.getString("title"));
                    recommendMoimDto.setContent(rs.getString("content"));
                    recommendMoimDto.setSido(rs.getString("sido"));
                    recommendMoimDto.setSigungu(rs.getString("sigungu"));
                    recommendMoimDto.setDong(rs.getString("dong"));
                    recommendMoimDto.setIsFreeEnter(rs.getString("isFreeEnter"));
                    recommendMoimDto.setMaxMember(rs.getInt("maxMember"));
                    recommendMoimDto.setCreateDate(rs.getDate("createDate"));
                    recommendMoimDto.setMoimLevel(rs.getInt("moimLevel"));
                    recommendMoimDtos.add(recommendMoimDto);
                }
                jsonObject.put("category" + limit, recommendMoimDtos);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            } finally {
                if (conn != null) conn.close();
                if (pstmt != null) pstmt.close();
                if (rs != null) rs.close();
            }
            limit++;
            if (limit >= 3) {
                break;
            }
        }

        return jsonObject;
    }
}
