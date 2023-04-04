package mvc.controller;

import mvc.entity.*;
import mvc.enums.BookingStatus;
import mvc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@Transactional
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
        String role = accountEntity.getUserRoles().iterator().next().getRole().name();
        model.addAttribute("accountEntity", accountEntity);
        if (accountEntity == null) {
            return "login";
        }
        // Check Booking cart is empty?
        List<BookingCartEntity> bookingCartEntity = bookingCartService.findByAccountId(accountEntity.getId());
        if (bookingCartEntity == null || bookingCartEntity.isEmpty()) {
            BookingCartEntity newBookingCartEntity = new BookingCartEntity();
            newBookingCartEntity.setId(1);
            newBookingCartEntity.setAccountEntity(accountEntity);
            bookingCartService.save(newBookingCartEntity);
            model.addAttribute("type", "listNull");
        } else {
            //Get cart items from session
            List<BookingCartItemEntity> listBookingCartItemEntity = (List<BookingCartItemEntity>) session.getAttribute("cartItemList");
            if (listBookingCartItemEntity == null || listBookingCartItemEntity.isEmpty()) {
                model.addAttribute("type", "listNull");
                model.addAttribute("msg", "Your Cart is empty, please add some thing <3 !");
            } else {
                setInfoBookingCart(listBookingCartItemEntity, session);
                session.setAttribute("cartItemList", listBookingCartItemEntity);
                List<AccountBankingEntity> accountBankingList = accountBankingService.findByAccountId(accountEntity.getId());
                if (accountBankingList.size() != 0 || !accountBankingList.isEmpty()) {
                    model.addAttribute("accountBanking", accountBankingList.get(0));
                    model.addAttribute("payment_status", "payment_available");
                } else {
                    model.addAttribute("accountBanking", new AccountBankingEntity());
                    model.addAttribute("payment_status", "payment_unavailable");
                }
            }
        }
        return "bookingcart";
    }

    @PostMapping(value="/saveuserinfo", produces = "text/plain;charset=UTF-8")
    public String saveUserInfo(@ModelAttribute("accountEntity") AccountEntity account) {
        AccountEntity accountEntity = accountService.findByEmail(account.getEmail());
        accountEntity.setFirst_name(account.getFirst_name());
        accountEntity.setLast_name(account.getLast_name());
        accountEntity.setAddress(account.getAddress());
        accountEntity.setPhone_number((account.getPhone_number()));
        accountService.save(accountEntity);
        return "redirect:/bookingcart";
    }

    @RequestMapping(value = "/payment", method = POST, produces = "text/plain;charset=UTF-8")
    public String saveNewAccountBanking(@ModelAttribute(name = "accountBanking") AccountBankingEntity accountBanking, HttpSession session) {
        // Get Account
        AccountEntity accountEntity = (AccountEntity) session.getAttribute("accountEntity");
        // Set Account to payment
        accountBanking.setAccountEntity(accountEntity);
        accountBankingService.save(accountBanking);
        return "redirect:/bookingcart";
    }

    @RequestMapping(value = "/checkout", method = POST, produces = "text/plain;charset=UTF-8")
    public String checkOut(HttpSession session, Model model, @RequestParam("note") String note) throws Exception {
        // Get account
        AccountEntity accountEntity = (AccountEntity) session.getAttribute("accountEntity");
        AccountBankingEntity accountBanking = accountBankingService.findByAccountId(accountEntity.getId()).get(0);
        bookingCartService.checkOut(accountEntity, accountBanking, session, model, note);
        return "successpage";
    }

    @GetMapping("/delete&cartitemid={id}")
    public String deleteCartItem(@PathVariable int id, HttpSession session) {
        List<BookingCartItemEntity> cartItemEntities = (List<BookingCartItemEntity>) session.getAttribute("cartItemList");
        for (BookingCartItemEntity cartItem : cartItemEntities) {
            if (cartItem.getId() == id) {
                cartItemEntities.remove(cartItem);
                bookingCartItemService.deleteById(cartItem.getId());
                break;
            }
        }
        session.setAttribute("cartItemList", cartItemEntities);
        return "redirect:/bookingcart";
    }

    @GetMapping("/success")
    public String successPage() {
        return "successpage";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    public void setInfoBookingCart(List<BookingCartItemEntity> listBookingCartItemEntity, HttpSession session) {
        int totalGuests = 0;
        double totalPrices = 0;
        int totalDays = 0;
        for (BookingCartItemEntity cartItem : listBookingCartItemEntity) {
            totalPrices += cartItem.getRoomEntity().getCategoryEntity().getPrice()*cartItem.getTotal_night();
            totalGuests += cartItem.getRoomEntity().getCategoryEntity().getMax_occupancy();
            totalDays += cartItem.getTotal_night();
        }

        session.setAttribute("totalDays", totalDays);
        session.setAttribute("totalGuests", totalGuests);
        session.setAttribute("totalPrices", totalPrices);
    }


}
