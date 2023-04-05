package mvc.entity;

import mvc.enums.BookingStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "booking")

public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "booking_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date booking_date;

    @Column(name = "booking_status")
    @Enumerated(EnumType.STRING)
    private BookingStatus booking_status;

    @Column(name = "note")
    private String note;

    @Column(name = "total_price")
    private double total_price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private AccountEntity accountEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "discount_id")
    private DiscountEntity discountEntity;

    @OneToMany(mappedBy = "bookingEntity", cascade = CascadeType.ALL)
    private List<BookingDetailEntity> bookingDetailEntities;

    @OneToMany(mappedBy = "bookingEntity", cascade = CascadeType.ALL)
    private List<PaymentEntity> paymentEntities;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(Date booking_date) {
        this.booking_date = booking_date;
    }

    public BookingStatus getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(BookingStatus booking_status) {
        this.booking_status = booking_status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public AccountEntity getAccountEntity() {
        return accountEntity;
    }

    public void setAccountEntity(AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
    }

    public DiscountEntity getDiscountEntity() {
        return discountEntity;
    }

    public void setDiscountEntity(DiscountEntity discountEntity) {
        this.discountEntity = discountEntity;
    }

    public List<BookingDetailEntity> getBookingDetailEntities() {
        return bookingDetailEntities;
    }

    public void setBookingDetailEntities(List<BookingDetailEntity> bookingDetailEntities) {
        this.bookingDetailEntities = bookingDetailEntities;
    }

    public List<PaymentEntity> getPaymentEntities() {
        return paymentEntities;
    }

    public void setPaymentEntities(List<PaymentEntity> paymentEntities) {
        this.paymentEntities = paymentEntities;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }
}
