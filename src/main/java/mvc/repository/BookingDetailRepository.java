package mvc.repository;

import mvc.entity.BookingDetailEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingDetailRepository extends CrudRepository<BookingDetailEntity, Integer> {
    @Query(value = "select * from booking_detail where id =?1", nativeQuery = true)
    BookingDetailEntity findById(int id);

    @Query(value = "select * from booking_detail where booking_id =?1", nativeQuery = true)
    List<BookingDetailEntity> findAllByBooking_id(int id);
    @Query(value = "SELECT distinct booking_detail.* FROM booking_detail\n" +
            "left join booking on booking_detail.booking_id = booking.id\n" +
            "left join account on booking.account_id = ?1 group by room_id", nativeQuery = true)
    List<BookingDetailEntity> findAllOfAccount(int accountId);
}
