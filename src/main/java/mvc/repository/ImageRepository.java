package mvc.repository;

import mvc.entity.ImageEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, Long> {
    List<ImageEntity> findAll();

    ImageEntity findById(long id);
    @Query(value="select image.* from image join category on image.category_id = category.id where category.category_name = ?;",nativeQuery = true)
    List<ImageEntity> findAllImageByCategory(String roomType);
}
