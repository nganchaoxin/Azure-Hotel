package mvc.repository;

import mvc.entity.BookingCartEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookingCartRepository extends CrudRepository<BookingCartEntity, Integer> {
    @Query(value = "select * from booking_cart where id =?1", nativeQuery = true)
    BookingCartEntity findById(int id);

    @Query(value = "select * from booking_cart where account_id =?1", nativeQuery = true)
    List<BookingCartEntity> findByAccountId(int id);

}
