package mvc.repository;

import mvc.entity.PaymentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends CrudRepository<PaymentEntity, Integer> {
    PaymentEntity findById(int id);

    @Query(value = "select * from payment where account_banking_id =?1", nativeQuery = true)
    List<PaymentEntity> findByAccountBankingId(int id);
}
