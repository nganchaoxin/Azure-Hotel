package mvc.repository;

import mvc.entity.DiscountEntity;
import mvc.entity.ImageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends CrudRepository<DiscountEntity, Integer> {
    DiscountEntity findById(int id);

    List<DiscountEntity> findAll();

}
