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

    @Query(value="select DISTINCT room.*,category.* from room left join category on category.id = room.category_id\n" +
    "left join booking_detail on room.id = booking_detail.room_id\n" +
    "\n" +
    "            where (category.category_name = :roomType) and (:guests <= category.max_occupancy )\n" +
    "            and room.id not in\n" +
    "            (SELECT room.id\n" +
    "            FROM room\n" +
    "            left join booking_detail ON booking_detail.room_id = room.id\n" +
    "            WHERE \n" +
    "                (:checkin BETWEEN booking_detail.booking_check_in AND booking_detail.booking_check_out\n" +
    "                OR :checkout BETWEEN booking_detail.booking_check_in AND booking_detail.booking_check_out\n" +
    "                OR :checkin < booking_detail.booking_check_out AND booking_detail.booking_check_in < :checkout) )", nativeQuery = true)
List<RoomEntity> getAvailableRooms(@Param("checkin") Date checkin, @Param("checkout") Date checkout,@Param("roomType")String roomType, @Param("guests") int guests);


}
