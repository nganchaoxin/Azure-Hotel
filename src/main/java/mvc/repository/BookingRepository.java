package mvc.repository;

import mvc.entity.BookingEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<BookingEntity, Integer> {
    @Query(value = "select * from booking where id =?1", nativeQuery = true)
    BookingEntity findById(int id);

    @Query(value = "select * from booking where account_id =?1", nativeQuery = true)
    List<BookingEntity> findByAccountId(int id);
}
