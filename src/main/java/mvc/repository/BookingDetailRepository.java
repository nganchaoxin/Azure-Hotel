package mvc.repository;

import mvc.entity.BookingDetailEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingDetailRepository extends CrudRepository<BookingDetailEntity, Integer> {
    @Query(value = "select * from booking_detail where id =?1", nativeQuery = true)
    BookingDetailEntity findById(int id);
    @Query(value = "select * from booking_detail where booking_id =?1", nativeQuery = true)
    List<BookingDetailEntity> findAllByBooking_id(int id);

    @Query(value="SELECT room.*\n" +
            "FROM booking_detail \n" +
            "JOIN room  ON booking_detail.room_id = room.id\n" +
            "WHERE \n" +
            "? BETWEEN booking_detail.booking_check_in AND booking_detail.booking_check_out\n" +
            "     or ?  BETWEEN booking_detail.booking_check_in AND booking_detail.booking_check_out;", nativeQuery = true)
    List<BookingDetailEntity> getBookingDetailHaveReservation(Date checkin, Date checkout);
}
