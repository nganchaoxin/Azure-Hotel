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
        List<RoomEntity> availableRooms = roomRepository.getRoomHaveReservation(roomType, guests, checkin, checkout);

        return availableRooms;


    }

    public RoomEntity findRoomById(int roomId) {
        return roomRepository.findRoomById(roomId);
    }
}
