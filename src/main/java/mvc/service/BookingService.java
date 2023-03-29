package mvc.service;

import mvc.entity.BookingEntity;
import mvc.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    public BookingEntity findById(int id) {
        return bookingRepository.findById(id);
    }

    public void save(BookingEntity newBookingEntity) {
        bookingRepository.save(newBookingEntity);
    }

    public List<BookingEntity> findByAccountId(int id) {
        return bookingRepository.findByAccountId(id);
    }
}
