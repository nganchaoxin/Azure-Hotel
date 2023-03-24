package mvc.service;

import mvc.entity.BookingDetailEntity;
import mvc.repository.BookingDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingDetailService {
    @Autowired
    BookingDetailRepository bookingDetailRepository;
    public BookingDetailEntity findById(int id) {
        return bookingDetailRepository.findById(id);
    }

    public List<BookingDetailEntity> findAllByBooking_id(int id) { return bookingDetailRepository.findAllByBooking_id(id);
    }

    public void save(BookingDetailEntity bookingDetail) { bookingDetailRepository.save(bookingDetail);
    }
}
