package mvc.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "account_banking")
public class AccountBankingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "card_number")
    private int card_number;
    @Column(name = "full_name")
    private String full_name;

    @Column (name="expired_date")
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date expired_date;

    @Column(name = "cvv")
    private int cvv;

    @Column(name = "balance")
    private double balance;

    @OneToMany(mappedBy = "accountBankingEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentEntity> paymentEntities;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private AccountEntity accountEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Date getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(Date expired_date) {
        this.expired_date = expired_date;
    }

    public int getCard_number() {
        return card_number;
    }

    public void setCard_number(int card_number) {
        this.card_number = card_number;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<PaymentEntity> getPaymentEntities() {
        return paymentEntities;
    }

    public void setPaymentEntities(List<PaymentEntity> paymentEntities) {
        this.paymentEntities = paymentEntities;
    }

    public AccountEntity getAccountEntity() {
        return accountEntity;
    }

    public void setAccountEntity(AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
    }
}
