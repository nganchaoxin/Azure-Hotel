package mvc.repository;

import mvc.entity.RoomEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<RoomEntity, Integer> {
    List<RoomEntity> findAll();

    @Query(value = "select room.* , category.* from room join category on category.id = room.category_id where room.id = ?", nativeQuery = true)
    RoomEntity findRoomById(int roomId);

    @Query(value = "SELECT DISTINCT room.*,category.* from room left join category on category.id = room.category_id  \n" +
            "                              LEFT JOIN booking_detail on room.id = booking_detail.room_id  \n" +
            "                            where (category.category_name = :roomType) and (:guests <= category.max_occupancy )  \n" +
            "                            and room.id not in  \n" +
            "                            (SELECT room.id FROM room left join booking_detail ON booking_detail.room_id = room.id left join booking on booking.id = booking_detail.booking_id \n" +
            "                            WHERE   \n" +
            "                                (booking_detail.booking_check_in <= :checkin <= booking_detail.booking_check_out  \n" +
            "                                OR booking_detail.booking_check_in <= :checkout <= booking_detail.booking_check_out  \n" +
            "                                OR :checkin <= booking_detail.booking_check_out AND booking_detail.booking_check_in <= :checkout)  \n" +
            "                           AND (booking.booking_status = 'COMPLETED') \n" +
            "                            );", nativeQuery = true)
    List<RoomEntity> getAvailableRooms(@Param("checkin") Date checkin, @Param("checkout") Date checkout, @Param("roomType") String roomType, @Param("guests") int guests);


}
