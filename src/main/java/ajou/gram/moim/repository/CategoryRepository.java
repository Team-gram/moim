package ajou.gram.moim.repository;

import ajou.gram.moim.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByCategoryParentId(int categoryParentId);
    Category findByCategoryId(int categoryId);
}
