package ajou.gram.moim.repository;

import ajou.gram.moim.dto.PrintChatDto;
import ajou.gram.moim.dto.RecommendPlaceDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class MoimChatRepositoryQuery {

    private final DataSource dataSource;

    public List<PrintChatDto> getMoimChats(long moimId) throws SQLException {
        String query =
                "select moim_chat.id, moim_id, name, user_id, createdAt, content, decoration " +
                        "from moim_chat " +
                        "inner join user on moim_chat.user_id = user.id " +
                        "where moim_id = ? " +
                        "order by createdAt asc";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<PrintChatDto> printChatDtos = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setLong(1, moimId);
            rs = pstmt.executeQuery();

            printChatDtos = new ArrayList<>();
            while(rs.next()) {
                PrintChatDto printChatDto = new PrintChatDto();
                printChatDto.setId(rs.getLong("id"));
                printChatDto.setMoimId(rs.getLong("moim_id"));
                printChatDto.setName(rs.getString("name"));
                printChatDto.setUserId(rs.getLong("user_id"));
                printChatDto.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                printChatDto.setContent(rs.getString("content"));
                printChatDto.setDecoration(rs.getInt("decoration"));
                printChatDtos.add(printChatDto);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            if (conn != null) conn.close();
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }

        return printChatDtos;
    }
}
