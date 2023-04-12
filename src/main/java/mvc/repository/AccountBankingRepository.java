package mvc.repository;

import mvc.entity.AccountBankingEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountBankingRepository extends CrudRepository<AccountBankingEntity, Integer> {
    @Query(value = "select * from account_banking where id =?1", nativeQuery = true)
    AccountBankingEntity findById(int id);

    @Query(value = "select * from account_banking where account_id =?1", nativeQuery = true)
    List<AccountBankingEntity> findByAccountId(int id);

    void deleteById(int id);
}
