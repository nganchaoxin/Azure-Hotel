package mvc.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
@Entity
@Table (name = "booking_detail")
public class BookingDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name="booking_check_in")
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date booking_check_in;

    @Column (name="booking_check_out")
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date booking_check_out;

    @Column(name = "total_price")
    private double total_price;

    @Column(name = "number_of_person")
    private int number_of_person;

    @Column(name = "number_of_adult")
    private int number_of_adult;

    @Column(name = "number_of_children")
    private int number_of_children;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "booking_id")
    private BookingEntity bookingEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "room_id")
    private RoomEntity roomEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBooking_check_in() {
        return booking_check_in;
    }

    public void setBooking_check_in(Date booking_check_in) {
        this.booking_check_in = booking_check_in;
    }

    public Date getBooking_check_out() {
        return booking_check_out;
    }

    public void setBooking_check_out(Date booking_check_out) {
        this.booking_check_out = booking_check_out;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public int getNumber_of_person() {
        return number_of_person;
    }

    public void setNumber_of_person(int number_of_person) {
        this.number_of_person = number_of_person;
    }

    public int getNumber_of_adult() {
        return number_of_adult;
    }

    public void setNumber_of_adult(int number_of_adult) {
        this.number_of_adult = number_of_adult;
    }

    public int getNumber_of_children() {
        return number_of_children;
    }

    public void setNumber_of_children(int number_of_children) {
        this.number_of_children = number_of_children;
    }

    public BookingEntity getBookingEntity() {
        return bookingEntity;
    }

    public void setBookingEntity(BookingEntity bookingEntity) {
        this.bookingEntity = bookingEntity;
    }

    public RoomEntity getRoomEntity() {
        return roomEntity;
    }

    public void setRoomEntity(RoomEntity roomEntity) {
        this.roomEntity = roomEntity;
    }
}
