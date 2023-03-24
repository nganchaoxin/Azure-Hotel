package mvc.repository;

import mvc.entity.AccountEntity;
import mvc.enums.UserStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Integer> {
    @Query(value = "select * from account where id =?1", nativeQuery = true)
    AccountEntity findById(int id);
    AccountEntity findByEmailLikeAndStatusLike(String email,
                                               UserStatus status);

    AccountEntity findByEmail(String userMail);
}
