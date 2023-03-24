package mvc.repository;

import mvc.entity.RoomEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface  RoomRepository extends CrudRepository<RoomEntity,Integer> {
    List<RoomEntity> findAll();
    @Query(value="select room.* , category.* from room join category on category.id = room.category_id where room.id = ?", nativeQuery = true)
    RoomEntity findRoomById(int roomId);

    @Query(value="select room.*,category.* from room inner join category on category.id = room.category_id\n" +
            "where (category.category_name = :roomType) and (:guests <= category.max_occupancy );"
            , nativeQuery = true)
    List<RoomEntity> getRoomByCategoryAndMaxPerson(@Param("roomType")String roomType, @Param("guests") int guests);
    @Query(value="SELECT room.*\n" +
            "FROM room \n" +
            "JOIN booking_detail ON booking_detail.room_id = room.id  \n" +
            "WHERE \n" +
            "    (:checkin BETWEEN booking_detail.booking_check_in AND booking_detail.booking_check_out \n" +
            "    OR :checkout BETWEEN booking_detail.booking_check_in AND booking_detail.booking_check_out\n" +
            "    OR :checkin < booking_detail.booking_check_out AND booking_detail.booking_check_in < :checkout) ;", nativeQuery = true)
    List<RoomEntity> getRoomHaveReservation(@Param("checkin") Date checkin, @Param("checkout") Date checkout);

}
