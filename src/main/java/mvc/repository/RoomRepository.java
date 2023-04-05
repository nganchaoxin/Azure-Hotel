package mvc.repository;

import mvc.entity.RoomEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoomRepository extends PagingAndSortingRepository<RoomEntity, Integer> {
    List<RoomEntity> findAll();

    @Query(value = "select room.* , category.* from room join category on category.id = room.category_id where room.id = ?", nativeQuery = true)
    RoomEntity findRoomById(int roomId);

    @Query(value = "SELECT DISTINCT room.*, category.*\n" +
            "FROM room\n" +
            "LEFT JOIN category ON category.id = room.category_id\n" +
            "LEFT JOIN booking_detail ON room.id = booking_detail.room_id\n" +
            "WHERE category.category_name = :roomType AND :guests <= category.max_occupancy\n" +
            "AND room.id NOT IN (\n" +
            "    SELECT room.id\n" +
            "    FROM room\n" +
            "    LEFT JOIN booking_detail ON booking_detail.room_id = room.id\n" +
            "    LEFT JOIN booking ON booking.id = booking_detail.booking_id\n" +
            "    WHERE (\n" +
            "        booking_detail.booking_check_in <= :checkin AND :checkin <= booking_detail.booking_check_out\n" +
            "        OR booking_detail.booking_check_in <= :checkout AND :checkout <= booking_detail.booking_check_out\n" +
            "        OR :checkin <= booking_detail.booking_check_out AND booking_detail.booking_check_in <= :checkout\n" +
            "    )\n" +
            "    AND booking.booking_status = 'COMPLETED'\n" +
            ")\n" +
            "ORDER BY room.id ASC",
            countQuery = "SELECT COUNT(*) FROM room LEFT JOIN category ON category.id = room.category_id WHERE category.category_name = :roomType AND :guests <= category.max_occupancy",
            nativeQuery = true)
    Page<RoomEntity> getAvailableRooms(@Param("checkin") Date checkin, @Param("checkout") Date checkout, @Param("roomType") String roomType, @Param("guests") int guests, Pageable pageable);


}