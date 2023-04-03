package mvc.service;

import mvc.entity.RoomEntity;
import mvc.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;

    public Page<RoomEntity> getAvailableRooms(Date checkin, Date checkout, String roomType, int guests, Pageable pageRequest) {
        Page<RoomEntity> availableRooms = roomRepository.getAvailableRooms(checkin, checkout, roomType, guests, pageRequest);

        return availableRooms;
    }

    public RoomEntity findRoomById(int roomId) {
        return roomRepository.findRoomById(roomId);
    }

    public List<RoomEntity> findAllRoom() {
        return roomRepository.findAll();
    }

    public void saveRoom(RoomEntity roomEntity) {
        roomRepository.save(roomEntity);
    }

    public void deleteById(int id) {
        roomRepository.deleteById(id);
    }
}
