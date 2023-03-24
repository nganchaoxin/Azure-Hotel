package mvc.service;

import mvc.entity.CategoryEntity;
import mvc.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<CategoryEntity> findAllCategory() { return categoryRepository.findAll();
    }
}
