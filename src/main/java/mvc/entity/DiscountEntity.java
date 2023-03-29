package mvc.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "discount")
public class DiscountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "discount_name")
    private String discount_name;

    @Column(name = "discount_amount")
    private double discount_value;

    @Column(name = "discount_start_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date discount_start_date;

    @Column(name = "discount_end_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date discount_end_date;

    @Column(name = "discount_description")
    private String discount_description;

    @Column(name = "discount_code")
    private String discount_code;

    @Column(name = "quantity")
    private int quantity;

    @OneToMany(mappedBy = "discountEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingEntity> bookingEntities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscount_name() {
        return discount_name;
    }

    public void setDiscount_name(String discount_name) {
        this.discount_name = discount_name;
    }

    public double getDiscount_value() {
        return discount_value;
    }

    public void setDiscount_value(double discount_value) {
        this.discount_value = discount_value;
    }

    public Date getDiscount_start_date() {
        return discount_start_date;
    }

    public void setDiscount_start_date(Date discount_start_date) {
        this.discount_start_date = discount_start_date;
    }

    public Date getDiscount_end_date() {
        return discount_end_date;
    }

    public void setDiscount_end_date(Date discount_end_date) {
        this.discount_end_date = discount_end_date;
    }

    public String getDiscount_description() {
        return discount_description;
    }

    public void setDiscount_description(String discount_description) {
        this.discount_description = discount_description;
    }

    public String getDiscount_code() {
        return discount_code;
    }

    public void setDiscount_code(String discount_code) {
        this.discount_code = discount_code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<BookingEntity> getBookingEntities() {
        return bookingEntities;
    }

    public void setBookingEntities(List<BookingEntity> bookingEntities) {
        this.bookingEntities = bookingEntities;
    }
}
