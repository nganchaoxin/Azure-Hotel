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

    public void deleteAll(List<BookingCartItemEntity> bookingCartItemDatabase) { bookingCartItemRepository.deleteAll();
    }
    public void saveAll(List<BookingCartItemEntity> bookingCartItemDatabase) {
        bookingCartItemRepository.saveAll(bookingCartItemDatabase);
    }
    public void deleteById(int id) {
        bookingCartItemRepository.deleteById(id);
    }


    public void deleteById(int id) { bookingCartItemRepository.deleteById(id);
    }

    public void deleteAll(List<BookingCartItemEntity> bookingCartItemEntities) { bookingCartItemRepository.deleteAll(bookingCartItemEntities);
    }
}
