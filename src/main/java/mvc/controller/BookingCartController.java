package mvc.controller;

import mvc.entity.*;
import mvc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
    RoomService roomService;

    @Autowired
    BookingCartItemService bookingCartItemService;

    @GetMapping()
    public String bookingCart(Model model, HttpSession session) {
        // Get account
        String userMail = (String) session.getAttribute("userEmail");
        AccountEntity accountEntity = accountService.findByEmail(userMail);

        if(accountEntity == null ){
            return "login";
        }
        session.setAttribute("accountEntity", accountEntity);

        // Get BookingCart By Account ID
        List<BookingCartEntity> bookingCart = bookingCartService.findByAccountId(accountEntity.getId());
        if(bookingCart.isEmpty()) {
            model.addAttribute("msg_account_banking", "Can't find your account banking!");
            model.addAttribute("status", "yes");
        } else{
            List<BookingCartItemEntity> cartItemEntities = bookingCartItemService.findAllByBookingCartId((int) bookingCart.get(0).getId());
            int totalOfRoom = 0;
            int totalOfGuests = 0;
            double totalPrice = 0;
            for (BookingCartItemEntity cartItem : cartItemEntities) {
                totalOfRoom += 1;
                totalOfGuests += cartItem.getRoomEntity().getCategoryEntity().getMax_occupancy();
                totalPrice += cartItem.getRoomEntity().getRoom_price();
            }
            session.setAttribute("totalOfRoom", totalOfRoom);
            session.setAttribute("totalOfGuests", totalOfGuests);
            session.setAttribute("totalOfPrice", totalPrice);

            List<BookingCartItemEntity> cartItemsInSession = (List<BookingCartItemEntity>) session.getAttribute("cartItemList");

            //Check account banking
            if(cartItemsInSession.size() > 0) {
                session.setAttribute("checkin", cartItemsInSession.get(0).getCheck_in());
                session.setAttribute("checkout", cartItemsInSession.get(0).getCheck_out());
                model.addAttribute("cart", "available");

                AccountBankingEntity accountBanking = accountBankingService.findByAccountId(accountEntity.getId());
                if (accountBanking != null) {
                    model.addAttribute("accountBanking", accountBanking);
                    model.addAttribute("payment_status", "payment_available");
                } else {
                    model.addAttribute("msg_account_banking", "Can't find your account banking!");
                    model.addAttribute("payment_status", "payment_unavailable");
                    model.addAttribute("accountBanking", new AccountBankingEntity());
//                    long totalDays = ChronoUnit.DAYS.between((Temporal) cartItemsInSession.get(0).getCheck_in(), (Temporal) cartItemsInSession.get(0).getCheck_out());
//                    session.setAttribute("totalDays", totalDays);
                }
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
    public String checkOut(HttpSession session) {
        // Create new booking entity
        AccountEntity accountEntity = (AccountEntity) session.getAttribute("accountEntity");
        BookingEntity newBookingEntity = new BookingEntity();
        newBookingEntity.setBooking_date(new Date());
        newBookingEntity.setBooking_status("New Booking");
        newBookingEntity.setAccountEntity(accountEntity);
        bookingService.save(newBookingEntity);

        //Create new booking detail
        List<BookingCartItemEntity> cartItems = (List<BookingCartItemEntity>) session.getAttribute("cartItemList");
        for (BookingCartItemEntity cartItem: cartItems) {
            BookingDetailEntity bookingDetail = new BookingDetailEntity();
            bookingDetail.setBookingEntity(newBookingEntity);
            bookingDetail.setRoomEntity(cartItem.getRoomEntity());
            bookingDetail.setNumber_of_person(cartItem.getRoomEntity().getCategoryEntity().getMax_occupancy());
            bookingDetail.setBooking_check_in(cartItem.getCheck_in());
            bookingDetail.setBooking_check_out(cartItem.getCheck_out());
            bookingDetailService.save(bookingDetail);
        }

        // Create new Payment
        double amount = (double) session.getAttribute("totalOfPrice");
        PaymentEntity newPayment = new PaymentEntity();
        newPayment.setBookingEntity(newBookingEntity);
        newPayment.setPayment_date(new Date());
        newPayment.setAmount(amount);
        newPayment.setAccountBankingEntity(accountBankingService.findByAccountId(accountEntity.getId()));
        paymentService.save(newPayment);

        // Update balance of account Banking
        AccountBankingEntity accountBanking = accountBankingService.findByAccountId(accountEntity.getId());
        double newBalance = accountBanking.getBalance() - amount;
        accountBanking.setBalance(newBalance);
        accountBankingService.save(accountBanking);

        // Clear Session List
        session.removeAttribute("listCartItem");
        return "/user/home";
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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

}
