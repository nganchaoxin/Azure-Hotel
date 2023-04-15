package mvc.repository;

import mvc.entity.BookingCartItemEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingCartItemRepository extends CrudRepository<BookingCartItemEntity, Integer> {
    @Query(value = "Select * from booking_cart_item where booking_cart_id=?1", nativeQuery = true)
    List<BookingCartItemEntity> findByBookingCartId(int id);

    void deleteById(int id);

    @Query(value = "SELECT booking_cart_item.*\n" +
            "FROM booking_cart_item\n" +
            "WHERE ( booking_cart_item.check_in < ?1 AND ?1 < booking_cart_item.check_out\n" +
            "OR booking_cart_item.check_in < ?2 AND ?2 < booking_cart_item.check_out\n" +
            "OR ?1 < booking_cart_item.check_out AND booking_cart_item.check_in < ?2)", nativeQuery = true)
    List<BookingCartItemEntity> listCartCheck(Date check_in, Date check_out);

}
