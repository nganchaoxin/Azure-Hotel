package mvc.controller;

import mvc.entity.*;
import mvc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/bookingcart")
public class BookingCartController {
    @Autowired
    BookingDetailService bookingDetailService;

    @Autowired
    PaymentService paymentService;
    @Autowired
    AccountService accountService;

    @Autowired
    BookingService bookingService;

    @Autowired
    AccountBankingService accountBankingService;

    @Autowired
    BookingCartService bookingCartService;

    @Autowired
    MailSender mailSender;

    @Autowired
    BookingCartItemService bookingCartItemService;

    @GetMapping()
    public String bookingCart(Model model, HttpSession session) {
        // Check null account
        String userMail = (String) session.getAttribute("userEmail");
        AccountEntity accountEntity = accountService.findByEmail(userMail);
        if(accountEntity == null){
            return "login";
        }
        //Get cart items from session
        List<BookingCartItemEntity> listBookingCartItemEntity = (List<BookingCartItemEntity>) session.getAttribute("cartItemList");
        if(listBookingCartItemEntity == null || listBookingCartItemEntity.isEmpty()) {
            model.addAttribute("type", "listNull");
            model.addAttribute("msg", "Your Cart is empty, please add some thing <3 !");
        }else {
            setInfoBookingCart(listBookingCartItemEntity,session);
            session.setAttribute("cartItemList", listBookingCartItemEntity);
            AccountBankingEntity accountBanking = accountBankingService.findByAccountId(accountEntity.getId());
            if (accountBanking != null) {
                model.addAttribute("accountBanking", accountBanking);
                model.addAttribute("payment_status", "payment_available");
            } else {
                model.addAttribute("accountBanking", new AccountBankingEntity());
                model.addAttribute("payment_status", "payment_unavailable");
            }
        }
        return "bookingcart";
    }

    @RequestMapping(value = "/payment", method = POST, produces = "text/plain;charset=UTF-8")
    public String saveNewAccountBanking(@ModelAttribute(name = "accountBanking") AccountBankingEntity accountBanking,HttpSession session) {
        // Get Account
        AccountEntity accountEntity = (AccountEntity) session.getAttribute("accountEntity");
        // Set Account to payment
        accountBanking.setAccountEntity(accountEntity);
        accountBankingService.save(accountBanking);
        return "redirect:/bookingcart";
    }

    @RequestMapping(value = "/checkout", method = POST, produces = "text/plain;charset=UTF-8")
    public String checkOut(HttpSession session, Model model) {
        // Get account
        AccountEntity accountEntity = (AccountEntity) session.getAttribute("accountEntity");
        AccountBankingEntity accountBanking = accountBankingService.findByAccountId(accountEntity.getId());
        if(accountBanking.getBalance() > (Double) session.getAttribute("totalPrices")) {
            //// Create new booking entity
            BookingEntity newBookingEntity = new BookingEntity();
            newBookingEntity.setBooking_date(new Date());
            newBookingEntity.setBooking_status("New Booking");
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
            newPayment.setAccountBankingEntity(accountBankingService.findByAccountId(accountEntity.getId()));
            paymentService.save(newPayment);

            // Update balance of account Banking
            double newBalance = accountBanking.getBalance() - (Double) session.getAttribute("totalPrices");
            accountBanking.setBalance(newBalance);
            accountBankingService.save(accountBanking);

            // Clear Session List and Database
            removeSession(session);
            BookingCartEntity bookingCartEntity = bookingCartService.findByAccountId(accountEntity.getId()).get(0);
            List<BookingCartItemEntity> bookingCartItemEntities = bookingCartItemService.findAllByBookingCartId(bookingCartEntity.getId());
            for (BookingCartItemEntity cartItem : bookingCartItemEntities) {
                bookingCartItemService.deleteById(cartItem.getId());
            }
            // Send email success booking new
            String email = accountEntity.getEmail();
            sendEmail(email, "Azure Hotel - New Booking Successfully", "Your Booking has been create successfully!");
            model.addAttribute("status", "completed");
            model.addAttribute("newBookingEntity", newBookingEntity);
        }
        else {
            model.addAttribute("status", "dismiss");
            model.addAttribute("accountEntity", accountEntity);
        }
        return "successpage";
    }

    @GetMapping("/delete&cartid={id}")
    public String deleteCartItem(@PathVariable int id, HttpSession session) {
        List<BookingCartItemEntity> cartItemEntities = (List<BookingCartItemEntity>) session.getAttribute("listCartItem");
        for (BookingCartItemEntity cartItem: cartItemEntities) {
            if(cartItem.getId() == id) {
                cartItemEntities.remove(cartItem);
                break;
            }
        }
        session.setAttribute("listCartItem", cartItemEntities);
        return "bookingcart";
    }

    @GetMapping("/success")
    public String successPage(){return "successpage";}

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    public void setInfoBookingCart(List<BookingCartItemEntity> listBookingCartItemEntity, HttpSession session) {
        int totalGuests = 0;
        double totalPrices = 0;
        for (BookingCartItemEntity cartItem: listBookingCartItemEntity) {
            totalPrices += cartItem.getRoomEntity().getCategoryEntity().getPrice();
            totalGuests += cartItem.getRoomEntity().getCategoryEntity().getMax_occupancy();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        int differenceInMillis = (int) (listBookingCartItemEntity.get(0).getCheck_out().getTime() - listBookingCartItemEntity.get(0).getCheck_in().getTime());
        int totalDays = differenceInMillis / (1000 * 60 * 60 * 24);
        session.setAttribute("totalDays",totalDays);
        session.setAttribute("totalGuests",totalGuests);
        session.setAttribute("totalPrices",totalPrices);
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
