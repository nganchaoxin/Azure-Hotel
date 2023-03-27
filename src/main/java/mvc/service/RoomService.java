package mvc.service;

import mvc.entity.RoomEntity;
import mvc.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;


    public List<RoomEntity> getAvailableRooms(Date checkin, Date checkout, String roomType, int guests) {
        List<RoomEntity> rooms = roomRepository.getRoomByCategoryAndMaxPerson(roomType, guests);
        List<RoomEntity> isBookedRooms = roomRepository.getRoomHaveReservation(checkin, checkout);
        if (isBookedRooms.isEmpty()) {
            return rooms;
        }
        List<RoomEntity> availableRooms = new ArrayList<>(rooms);
        availableRooms.removeAll(isBookedRooms);

        return availableRooms;


    }

    public RoomEntity findRoomById(int roomId) {
        return roomRepository.findRoomById(roomId);
    }
    public List<RoomEntity> findAllRoom() {
        return roomRepository.findAll();
    }
    public void saveRoom(RoomEntity roomEntity){
        roomRepository.save(roomEntity);
    }
}
