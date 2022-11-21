package ajou.gram.moim.repository;

import ajou.gram.moim.domain.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@AllArgsConstructor
public class CategoryRepositoryQuery {

    private final EntityManager em;

    public List<Category> findByCategoryIdAtCategoryParentId(int categoryId) {
        String query = "select m from category m where m.categoryParentId = :categoryId";
        return em.createQuery(query, Category.class).setParameter("categoryId", categoryId).setMaxResults(20).getResultList();
    }
}
