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

    public List<CategoryEntity> findAllCategory() {
        return categoryRepository.findAll();
    }
    public void save(CategoryEntity category){
        categoryRepository.save(category);
    }

    public CategoryEntity findById(int categoryId){
        return categoryRepository.findById(categoryId);

    }

    public void deleteById(int id) {
        categoryRepository.deleteById(id);

    }
}
