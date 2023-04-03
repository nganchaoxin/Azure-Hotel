package mvc.service;

import mvc.entity.*;
import mvc.enums.BookingStatus;
import mvc.repository.BookingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class BookingCartService {
    @Autowired
    MailSender mailSender;
    @Autowired
    BookingCartRepository bookingCartRepository;

    @Autowired
    BookingCartService bookingCartService;

    @Autowired
    BookingService bookingService;
    @Autowired
    PaymentService paymentService;
    @Autowired
    BookingDetailService bookingDetailService;

    @Autowired
    AccountBankingService accountBankingService;

    @Autowired
    BookingCartItemService bookingCartItemService;


    public BookingCartEntity findById(int id) {
        return bookingCartRepository.findById(id);
    }

    public void save(BookingCartEntity bookingCartEntity) {
        bookingCartRepository.save(bookingCartEntity);
    }

    public List<BookingCartEntity> findByAccountId(int id) {
        return bookingCartRepository.findByAccountId(id);
    }

    public void deleteAll() {
        bookingCartRepository.deleteAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void checkOut(AccountEntity accountEntity, AccountBankingEntity accountBanking, HttpSession session, Model model) throws Exception {
        try {
            if (accountBanking.getBalance() > (Double) session.getAttribute("totalPrices")) {
                //// Create new booking entity
                BookingEntity newBookingEntity = new BookingEntity();
                newBookingEntity.setBooking_date(new Date());
                newBookingEntity.setBooking_status(BookingStatus.COMPLETED);
                newBookingEntity.setAccountEntity(accountEntity);
                newBookingEntity.setTotal_price((Double) session.getAttribute("totalPrices"));
                bookingService.save(newBookingEntity);

                //Create new booking detail
                List<BookingCartItemEntity> cartItems = (List<BookingCartItemEntity>) session.getAttribute("cartItemList");
                for (BookingCartItemEntity cartItem : cartItems) {
                    bookingDetailService.createNewBookingDetail(cartItem, newBookingEntity);
                }
                // Create new Payment
                PaymentEntity newPayment = new PaymentEntity();
                newPayment.setBookingEntity(newBookingEntity);
                newPayment.setPayment_date(new Date());
                newPayment.setAmount((Double) session.getAttribute("totalPrices"));
                newPayment.setAccountBankingEntity(accountBankingService.findByAccountId(accountEntity.getId()).get(0));
                paymentService.save(newPayment);

                // Update balance of account Banking
                double newBalance = accountBanking.getBalance() - (Double) session.getAttribute("totalPrices");
                accountBanking.setBalance(newBalance);
                accountBankingService.save(accountBanking);

                // Clear Session List and Database
                removeSession(session);

                BookingCartEntity bookingCartEntity = bookingCartService.findByAccountId(accountEntity.getId()).get(0);
                List<BookingCartItemEntity> bookingCartItemEntities = bookingCartItemService.findAllByBookingCartId(bookingCartEntity.getId());
                bookingCartItemService.deleteAll(bookingCartItemEntities);
                // Send email success booking new
                String email = accountEntity.getEmail();
                sendEmail(email, "Azure Hotel - New Booking Successfully", "Your Booking has been create successfully!");
                model.addAttribute("status", "completed");
                model.addAttribute("newBookingEntity", newBookingEntity);
                model.addAttribute("bookingDetailEntity", bookingDetailService.findByBookingId(newBookingEntity.getId()).get(0));

            } else {
                throw new Exception("An account error occurs. Procedure. Check whether the account balance is sufficient for the calling party.");
            }
        }catch (Exception ex) {
            model.addAttribute("status", "dismiss");
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("accountEntity", accountEntity);
        }
    }
    public void removeSession(HttpSession session) {
        session.removeAttribute("cartItemList");
        session.removeAttribute("totalPrices");
        session.removeAttribute("totalGuests");
        session.removeAttribute("totalDays");
    }

    public void sendEmail(String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);

        mailSender.send(mailMessage);
    }
}
