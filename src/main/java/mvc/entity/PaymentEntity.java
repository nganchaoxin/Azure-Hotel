package mvc.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payment")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "amount")
    private double amount;

    @Column(name = "payment_date")
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date payment_date;

    @Column(name = "note")
    private String note;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "account_banking_id")
    private AccountBankingEntity accountBankingEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "booking_id")
    private BookingEntity bookingEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public AccountBankingEntity getAccountBankingEntity() {
        return accountBankingEntity;
    }

    public void setAccountBankingEntity(AccountBankingEntity accountBankingEntity) {
        this.accountBankingEntity = accountBankingEntity;
    }

    public BookingEntity getBookingEntity() {
        return bookingEntity;
    }

    public void setBookingEntity(BookingEntity bookingEntity) {
        this.bookingEntity = bookingEntity;
    }
}
