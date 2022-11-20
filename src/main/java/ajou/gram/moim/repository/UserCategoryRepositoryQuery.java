package ajou.gram.moim.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@AllArgsConstructor
public class UserCategoryRepositoryQuery {

    private final EntityManager em;

    public List<Integer> findByUserId(long id) {
        String query = "" +
                "select ";
        return null;
    }
}
