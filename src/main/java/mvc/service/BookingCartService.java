package mvc.service;

import mvc.entity.BookingCartEntity;
import mvc.repository.BookingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingCartService {
    @Autowired
    BookingCartRepository bookingCartRepository;


    public BookingCartEntity findById(int id) {
        return bookingCartRepository.findById(id);
    }

    public void save(BookingCartEntity bookingCartEntity) {
        bookingCartRepository.save(bookingCartEntity);
    }

    public List<BookingCartEntity> findByAccountId(int id) {
        return bookingCartRepository.findByAccountId(id);
    }

    public void deleteAll() {
        bookingCartRepository.deleteAll();
    }
}
