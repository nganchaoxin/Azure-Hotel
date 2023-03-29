package mvc.service;

import mvc.entity.BookingCartItemEntity;
import mvc.entity.BookingDetailEntity;
import mvc.entity.BookingEntity;
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

    public List<BookingDetailEntity> findAllByBooking_id(int id) {
        return bookingDetailRepository.findAllByBooking_id(id);
    }

    public void save(BookingDetailEntity bookingDetail) {
        bookingDetailRepository.save(bookingDetail);
    }

    public List<BookingDetailEntity> findByBookingId(int id) {
        return bookingDetailRepository.findAllByBooking_id(id);
    }

    public void createNewBookingDetail(BookingCartItemEntity cartItem, BookingEntity newBookingEntity) {
        BookingDetailEntity bookingDetail = new BookingDetailEntity();
        bookingDetail.setBookingEntity(newBookingEntity);
        bookingDetail.setRoomEntity(cartItem.getRoomEntity());
        bookingDetail.setNumber_of_person(cartItem.getRoomEntity().getCategoryEntity().getMax_occupancy());
        bookingDetail.setBooking_check_in(cartItem.getCheck_in());
        bookingDetail.setBooking_check_out(cartItem.getCheck_out());
        bookingDetailRepository.save(bookingDetail);
    }
}
