package ajou.gram.moim.service;

import ajou.gram.moim.domain.Category;
import ajou.gram.moim.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SearchService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategories(int parentId) {
        return categoryRepository.findByCategoryParentId(parentId);
    }
}
