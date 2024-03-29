package mvc.entity;

import mvc.enums.Gender;
import mvc.enums.UserStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "account")
public class AccountEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, length = 100)
    private String email;

    private String password;

    @Column(name = "username")
    private String username;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "account_role",
            joinColumns = @JoinColumn(name = "account_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id"))
    private Set<RoleEntity> userRoles;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "photo")
    @Lob
    private byte[] photo;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth_date;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "address")
    private String address;

    @Column(name = "token")
    private String token;

    @Column(name = "registration_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registration_date;

    @OneToMany(mappedBy = "accountEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountBankingEntity> accountBankingEntities;
    @OneToMany(mappedBy = "accountEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentEntity> paymentEntities;

    @OneToMany(mappedBy = "accountEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingEntity> bookingEntities;

    @OneToMany(mappedBy = "accountEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RatingEntity> ratingEntities;

    @OneToOne(mappedBy = "accountEntity", cascade = CascadeType.ALL)
    private BookingCartEntity bookingCartEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Set<RoleEntity> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<RoleEntity> userRoles) {
        this.userRoles = userRoles;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }

    public List<AccountBankingEntity> getAccountBankingEntities() {
        return accountBankingEntities;
    }

    public void setAccountBankingEntities(List<AccountBankingEntity> accountBankingEntities) {
        this.accountBankingEntities = accountBankingEntities;
    }

    public List<BookingEntity> getBookingEntities() {
        return bookingEntities;
    }

    public void setBookingEntities(List<BookingEntity> bookingEntities) {
        this.bookingEntities = bookingEntities;
    }

    public BookingCartEntity getBookingCartEntity() {
        return bookingCartEntity;
    }

    public void setBookingCartEntity(BookingCartEntity bookingCartEntity) {
        this.bookingCartEntity = bookingCartEntity;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public List<PaymentEntity> getPaymentEntities() {
        return paymentEntities;
    }

    public void setPaymentEntities(List<PaymentEntity> paymentEntities) {
        this.paymentEntities = paymentEntities;
    }
}