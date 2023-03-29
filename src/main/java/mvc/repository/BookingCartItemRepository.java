package mvc.repository;

import mvc.entity.BookingCartItemEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingCartItemRepository extends CrudRepository<BookingCartItemEntity, Integer> {
    @Query(value = "Select * from booking_cart_item where booking_cart_id=?1", nativeQuery = true)
    List<BookingCartItemEntity> findByBookingCartId(int id);

    void deleteById(int id);


}
