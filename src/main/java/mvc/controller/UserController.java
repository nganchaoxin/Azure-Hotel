package mvc.controller;

import mvc.entity.*;
import mvc.enums.BookingStatus;
import mvc.enums.Gender;
import mvc.enums.RoomStatus;
import mvc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    AccountService accountService;

    @Autowired
    BookingService bookingService;
    @Autowired
    MailSender mailSender;
    @Autowired
    BookingDetailService bookingDetailService;

    @Autowired
    AccountBankingService accountBankingService;

    @Autowired
    PaymentService paymentService;

    @RequestMapping(value = "/account", method = GET)
    public String userHome(Model model, HttpSession session) {
        AccountEntity accountEntity = (AccountEntity) session.getAttribute("accountEntity");
        model.addAttribute("accountEntity", accountEntity);
        setGenderDropDownList(model);
        return "user/account";
    }

    @RequestMapping(value = "/account", method = POST, produces = "text/plain;charset=UTF-8")
    public String saveUser(@ModelAttribute("accountEntity") AccountEntity account,
                           @RequestParam(name = "first_name") String first_name,
                           @RequestParam(name = "last_name") String last_name,
                           @RequestParam(name = "address") String address,
                           @RequestParam(name = "username") String username,
                           @RequestParam(name = "phone_number") String phone_number,
                           @RequestParam(name = "gender") Gender gender,
                           @RequestParam(name = "birth_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birth_date,
                           HttpSession session, Model model) {
        AccountEntity accountEntity = (AccountEntity) session.getAttribute("accountEntity");
        accountEntity.setFirst_name(first_name);
        accountEntity.setLast_name(last_name);
        accountEntity.setAddress(address);
        accountEntity.setUsername(username);
        accountEntity.setPhone_number(phone_number);
        accountEntity.setGender(gender);
        accountEntity.setBirth_date(birth_date);
        accountService.save(accountEntity);
        setGenderDropDownList(model);
        return "user/account";
    }

    @GetMapping("/forgotpassword")
    public String forgotPassword(@ModelAttribute("msg") String msg, Model model) {
        model.addAttribute("page", "password");
        return "user/forgot_password";
    }

    @PostMapping("/forgotpassword")
    public String confirmEmail(@RequestParam String email, HttpSession session, Model model) {
        AccountEntity accountEntity = (AccountEntity) session.getAttribute("accountEntity");
        model.addAttribute("page", "sendEmailSuccess");

        if (!accountEntity.getEmail().equals(email)) {
            model.addAttribute("msg", "Form submitted successfully!");
            model.addAttribute("type", "wrongEmail");
            model.addAttribute("page", "password");
        } else {
            model.addAttribute("page", "sendEmailSuccess");
        }
//        sendEmail(email, "Azure Hotel Account - Forgot your password", "You have been send a request forgot your password, please click here to set new pass word:"+"http://localhost:8080/Azure-Hotel/user/forgotpassword&id="+accountEntity.getId());}
        return "user/forgot_password";
    }

    @GetMapping("/forgotpassword&id={id}")
    public String changePassWord(Model model) {
        model.addAttribute("page", "changePassword");
        return "user/forgot_password";
    }

    @PostMapping("/forgotpassword&id={id}")
    public String saveNewPassword(@PathVariable int id, @RequestParam String password, @RequestParam String password_two, HttpSession session, Model model) {
        AccountEntity accountEntity = accountService.findById(id);
        if (password.equals(password_two)) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            accountEntity.setPassword(encoder.encode(password));
            accountService.save(accountEntity);
        }
        model.addAttribute("page", "changePasswordSuccess");
        session.setAttribute("accountEntity", accountEntity);
        return "user/forgot_password";
    }

    @RequestMapping(value = "/cardpayment", method = GET)
    public String paymentCard(HttpSession session, Model model) {
        AccountEntity accountEntity = (AccountEntity) session.getAttribute("accountEntity");
        List<AccountBankingEntity> accountBankingEntityList = accountBankingService.findByAccountId(accountEntity.getId());
        if(accountBankingEntityList == null || accountBankingEntityList.isEmpty()) {
            model.addAttribute("status", "noCard");
            model.addAttribute("accountBankingEntity", new AccountBankingEntity());
        }else {
            AccountBankingEntity accountBankingEntity = accountBankingEntityList.get(0);
            model.addAttribute("accountBankingEntity", accountBankingEntity);
            model.addAttribute("status", "haveCard");
        }
        return "user/payment_card";
    }

    @RequestMapping(value = "/cardpayment", method = POST, produces = "text/plain;charset=UTF-8")
    public String paymentCard(@ModelAttribute(name = "accountBankingEntity") AccountBankingEntity accountBankingEntity, HttpSession session) {
        AccountEntity accountEntity = (AccountEntity) session.getAttribute("accountEntity");
        accountBankingEntity.setAccountEntity(accountEntity);
        accountBankingService.save(accountBankingEntity);
        return "user/payment_card";
    }

    @GetMapping("/paymenthistory")
    public String paymentHistory(HttpSession session, Model model) {
        AccountEntity accountEntity = (AccountEntity) session.getAttribute("accountEntity");
        List<AccountBankingEntity> accountBankingEntityList = accountBankingService.findByAccountId(accountEntity.getId());
        if(accountBankingEntityList == null || accountBankingEntityList.isEmpty()) {
            model.addAttribute("status", "paymentNull");
        }else {
            List<PaymentEntity> paymentEntityList = paymentService.findByAccountBankingId(accountBankingEntityList.get(0).getId());
            model.addAttribute("paymentEntityList", paymentEntityList);
            model.addAttribute("status", "paymentNull");
        }
        return "user/payment_history";
    }

    @GetMapping("/booking")
    public String booking(HttpSession session, Model model) {
        AccountEntity accountEntity = (AccountEntity) session.getAttribute("accountEntity");
        List<BookingEntity> bookingEntityList = bookingService.findByAccountId(accountEntity.getId());
        model.addAttribute("bookingEntityList", bookingEntityList);
        return "user/booking";
    }

    @PostMapping("/cancelbooking&id={id}")
    public String cancelBooking(@PathVariable int id, RedirectAttributes redirectAttributes, HttpSession session, Model model){
        BookingEntity bookingEntity = bookingService.findById(id);
        bookingEntity.setBooking_status(BookingStatus.CANCEL);
        bookingService.save(bookingEntity);
        redirectAttributes.addFlashAttribute("Cancel Booking Successfully!", "msg");
        AccountEntity accountEntity = (AccountEntity) session.getAttribute("accountEntity");
        List<BookingEntity> bookingEntityList = bookingService.findByAccountId(accountEntity.getId());
        model.addAttribute("bookingEntityList", bookingEntityList);
        return "user/booking";
    }

    @GetMapping("/bookingdetail&bookingid={id}")
    public String bookingDetail(Model model, @PathVariable(name = "id") int id) {
        List<BookingDetailEntity> bookingDetailEntities = bookingDetailService.findByBookingId(id);
        model.addAttribute("bookingDetails", bookingDetailEntities);
        return "user/booking_detail";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    public void sendEmail(String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);

        mailSender.send(mailMessage);
    }

    public void setGenderDropDownList(Model model) {
        List<Gender> genderList = new ArrayList<>();
        genderList.add(Gender.MALE);
        genderList.add(Gender.FEMALE);

        model.addAttribute("genderList", genderList);
    }

}
