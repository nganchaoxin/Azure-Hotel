package mvc.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "booking_cart_item")
public class BookingCartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "check_in")
    private Date check_in;

    @Column(name = "check_out")
    private Date check_out;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "booking_cart_id")
    private BookingCartEntity bookingCartEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private RoomEntity roomEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookingCartEntity getBookingCartEntity() {
        return bookingCartEntity;
    }

    public void setBookingCartEntity(BookingCartEntity bookingCartEntity) {
        this.bookingCartEntity = bookingCartEntity;
    }

    public RoomEntity getRoomEntity() {
        return roomEntity;
    }

    public void setRoomEntity(RoomEntity roomEntity) {
        this.roomEntity = roomEntity;
    }

    public Date getCheck_in() {
        return check_in;
    }

    public void setCheck_in(Date check_in) {
        this.check_in = check_in;
    }

    public Date getCheck_out() {
        return check_out;
    }

    public void setCheck_out(Date check_out) {
        this.check_out = check_out;
    }
}
