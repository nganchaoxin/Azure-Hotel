package mvc.repository;

import mvc.entity.ImageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, Long> {
    List<ImageEntity> findAll();

    ImageEntity findById(long id);
}
