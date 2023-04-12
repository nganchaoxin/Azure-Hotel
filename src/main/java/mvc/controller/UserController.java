package mvc.controller;

import mvc.entity.*;
import mvc.enums.BookingStatus;
import mvc.enums.Gender;
import mvc.service.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    @Autowired
    JavaMailSender javaMailSender;

    @GetMapping(value = "/account")
    public String account(Model model, HttpSession session) {
        model.addAttribute("accountEntity", session.getAttribute("accountEntity"));
        setGenderDropDownList(model);
        return "user/account";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String saveUser(
            @RequestPart(name = "photo") MultipartFile photo,
            @RequestParam(name = "first_name") String first_name,
            @RequestParam(name = "last_name") String last_name,
            @RequestParam(name = "address") String address,
            @RequestParam(name = "username") String username,
            @RequestParam(name = "phone_number") String phone_number,
            @RequestParam(name = "gender") Gender gender,
            @RequestParam(name = "birth_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birth_date,
            HttpSession session, Model model, RedirectAttributes redirectAttributes) throws IOException {
        AccountEntity accountEntity = accountService.findByEmail(((AccountEntity) session.getAttribute("accountEntity")).getEmail());
        accountEntity.setFirst_name(first_name);

        if (photo.getOriginalFilename() == "") {
            byte[] ph = accountEntity.getPhoto();
            accountEntity.setPhoto(ph);
        } else {
            accountEntity.setPhoto(photo.getBytes());
        }

        accountEntity.setLast_name(last_name);
        accountEntity.setAddress(address);
        accountEntity.setUsername(username);
        accountEntity.setPhone_number(phone_number);
        accountEntity.setGender(gender);
        accountEntity.setBirth_date(birth_date);
        accountService.save(accountEntity);
        setGenderDropDownList(model);
        session.setAttribute("accountEntity", accountEntity);
        redirectAttributes.addFlashAttribute("success_msg", "Change successfully!");
        return "redirect:/user/account";
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
            model.addAttribute("msg", "Email is not correct!");
            model.addAttribute("type", "wrongEmail");
            model.addAttribute("page", "password");
        } else {
            model.addAttribute("page", "sendEmailSuccess");
            String encodedString = UUID.randomUUID().toString();
            accountEntity.setToken(encodedString);
            accountService.save(accountEntity);
            String body = "<h1>Change your Password</h1>\n" +
                    "<p>To continue changing your password, click here:</p>\n" +
                    "<a href=http://localhost:8080/Azure-Hotel/user/forgotpasswordaccess?token=" + encodedString + " class=\"btn btn-primary\" type=\"button\">Change your password</a>\n" +
                    "<p>Best regards,<br>The Azure Hotel team</p>";
            sendEmail(email, "Azure Hotel -Signup new account", body);
        }
        return "user/forgot_password";
    }

    @GetMapping("/forgotpasswordaccess")
    public String changePassWord(@RequestParam("token") String token, Model model) {
        AccountEntity accountEntity = accountService.findByToken(token);
        if (accountEntity != null) {
            model.addAttribute("page", "changePassword");
            return "user/forgot_password";
        }else {
            return "not_found";
        }
    }

    @PostMapping("/forgotpassword&id={id}")
    public String saveNewPassword(@PathVariable("id") int id, @RequestParam String password, @RequestParam String password_two, HttpSession session, Model model) {
        AccountEntity accountEntity = accountService.findById(id);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean passwordMatches = encoder.matches(password, accountEntity.getPassword());
        if (!passwordMatches && password.equals(password_two)) {
            accountEntity.setPassword(encoder.encode(password));
            accountEntity.setToken(null);
            accountService.save(accountEntity);
            session.setAttribute("page", "changePasswordSuccess");
            session.setAttribute("accountEntity", accountEntity);
        } else {
            model.addAttribute("page", "changePassword");
            model.addAttribute("type", "wrong");
            model.addAttribute("msg", "Password was existing or password repeat not same!");
        }
        return "user/forgot_password";
    }

    @RequestMapping(value = "/cardpayment", method = GET)
    public String paymentCard(HttpSession session, Model model) {
        AccountEntity accountEntity = (AccountEntity) session.getAttribute("accountEntity");
        List<AccountBankingEntity> accountBankingEntityList = accountBankingService.findByAccountId(accountEntity.getId());
        if (accountBankingEntityList == null || accountBankingEntityList.isEmpty()) {
            model.addAttribute("status", "noCard");
            model.addAttribute("accountBankingEntity", new AccountBankingEntity());
        } else {
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
        session.setAttribute("success_msg", "Add new account banking successfully!");
        return "user/payment_card";
    }

    @RequestMapping(value = "/deletecard{id}", method = GET)
    public String deleteCard(@PathVariable int id, HttpSession session) {
        accountBankingService.deleteById(id);
        session.setAttribute("success_msg", "Delete account banking successfully!");
        return "redirect: cardpayment";
    }


    @GetMapping("/paymenthistory")
    public String paymentHistory(HttpSession session, Model model) {
        AccountEntity accountEntity = (AccountEntity) session.getAttribute("accountEntity");
        List<PaymentEntity> paymentEntityList = paymentService.findByAccountId(accountEntity.getId());
        if (paymentEntityList == null || paymentEntityList.isEmpty()) {
            model.addAttribute("status", "paymentNull");
        } else {
            model.addAttribute("paymentEntityList", paymentEntityList);
            model.addAttribute("status", "paymentNotNull");
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
    public String cancelBooking(@PathVariable int id, HttpSession session, Model model, HttpServletRequest request) {
        AccountEntity accountEntity = (AccountEntity) session.getAttribute("accountEntity");
        List<AccountBankingEntity> accountBankingEntityList = accountBankingService.findByAccountId(accountEntity.getId());
        if(accountBankingEntityList == null || accountBankingEntityList.isEmpty()) {
            request.setAttribute("msg_fail", "Please insert your payment card before cancel this booking!");
            List<BookingEntity> bookingEntityList = bookingService.findByAccountId(accountEntity.getId());
            model.addAttribute("bookingEntityList", bookingEntityList);
        }else {
            AccountBankingEntity accountBankingEntity = accountBankingEntityList.get(0);
            BookingEntity bookingEntity = bookingService.findById(id);
            bookingEntity.setBooking_status(BookingStatus.CANCEL);
            bookingService.save(bookingEntity);
            accountBankingEntity.setBalance(accountBankingEntity.getBalance() + bookingEntity.getTotal_price());
            accountBankingService.save(accountBankingEntity);
            request.setAttribute("msg", "Cancel Booking Successfully!");

            String email = accountEntity.getEmail();
            String body = "<h1>Azure Hotel - Cancel booking</h1>\n" +
                    "<p>Your booking has been successfully canceled.</p>\n" +
                    "<p>Order #: "+bookingEntity.getId()+"</p>\n" +
                    "<p>Order Date: "+bookingEntity.getBooking_date()+"</p>\n" +
                    "<p>Thank you!</p>\n" +
                    "<p>Best regards,<br>The Azure Hotel team</p>";

            sendEmail(email, "Azure Hotel - New Booking Successfully", body);

            List<BookingEntity> bookingEntityList = bookingService.findByAccountId(accountEntity.getId());
            model.addAttribute("bookingEntityList", bookingEntityList);
        }
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

    public void sendEmail(String recipient, String subject, String body) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(recipient);
            helper.setSubject(subject);
            helper.setText(body, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }

    public void setGenderDropDownList(Model model) {
        List<Gender> genderList = new ArrayList<>();
        genderList.add(Gender.MALE);
        genderList.add(Gender.FEMALE);

        model.addAttribute("genderList", genderList);
    }

    @RequestMapping(value = "/getImagePhoto/{id}")
    public void getImagePhoto(HttpServletResponse response, @PathVariable("id") int id) throws Exception {
        response.setContentType("image/jpeg");
        AccountEntity accountEntity = accountService.findById(id);
        byte[] ph = accountEntity.getPhoto();
        InputStream inputStream = new ByteArrayInputStream(ph);
        IOUtils.copy(inputStream, response.getOutputStream());
    }
}
