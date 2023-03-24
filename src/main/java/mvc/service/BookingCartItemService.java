package mvc.service;

import mvc.entity.BookingCartItemEntity;
import mvc.repository.BookingCartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingCartItemService {
    @Autowired
    BookingCartItemRepository bookingCartItemRepository;
    public void save(BookingCartItemEntity bookingCartItemEntity) { bookingCartItemRepository.save(bookingCartItemEntity);
    }

    public List<BookingCartItemEntity> findAllByBookingCartId(int id) { return bookingCartItemRepository.findByBookingCartId(id);
    }

    public void clearAll() { bookingCartItemRepository.deleteAll();
    }

}
