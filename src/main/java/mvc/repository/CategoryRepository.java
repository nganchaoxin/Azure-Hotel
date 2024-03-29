package mvc.repository;

import mvc.entity.CategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {
    List<CategoryEntity> findAll();

    CategoryEntity findById(int id);

    @Query(value = "select category.* from category where category_name = ?", nativeQuery = true)
    CategoryEntity findByCategoryName(String categoryName);
}
