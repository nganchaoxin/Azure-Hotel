package mvc.repository;

import mvc.entity.PaymentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<PaymentEntity, Integer> {
    PaymentEntity findById(int id);
}
