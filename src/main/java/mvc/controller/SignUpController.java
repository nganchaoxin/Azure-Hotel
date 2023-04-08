package mvc.controller;

import mvc.entity.AccountEntity;
import mvc.entity.BookingCartEntity;
import mvc.entity.RoleEntity;
import mvc.enums.UserStatus;
import mvc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static mvc.enums.Role.ROLE_USER;


@Controller
public class SignUpController {
    @Autowired
    AccountService accountService;
    @Autowired
    MailSender mailSender;

    @Autowired
    JavaMailSender javaMailSender;
    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("signup")
    public String signUpPage(Model model, @RequestParam(value = "error", required = false) boolean error) {
        if (error) {
            model.addAttribute("message", "Login Fail!!!");
        }
        model.addAttribute("newAccount", new AccountEntity());
        return "signup";

    }

    @PostMapping("signup")
    public String signUp(@ModelAttribute(name = "newAccount") AccountEntity accountEntity, Model model, @RequestParam String password_two) {
        // Check password
        AccountEntity accountTest = accountService.findByEmail(accountEntity.getEmail());
        if (!accountEntity.getPassword().equals(password_two)) {
            model.addAttribute("message", "Password repeat is not correct!");
            model.addAttribute("type", "wrongEmail");
            return "signup";
        } else if (accountTest != null) {
            model.addAttribute("message", "Email has been signed up!");
            model.addAttribute("type", "wrongPass");
            return "signup";
        }
        // Encoder password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        accountEntity.setPassword(encoder.encode(accountEntity.getPassword()));
        RoleEntity role = new RoleEntity();
        role.setRole(ROLE_USER);
        role.setId(2);

        String email = accountEntity.getEmail();
        String encodedString = UUID.randomUUID().toString();

        // Set role
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(role);
        accountEntity.setUserRoles(roles);
        accountEntity.setStatus(UserStatus.UNACTIVE);
        accountEntity.setRegistration_date(new Date());
        accountEntity.setToken(encodedString);
        accountService.save(accountEntity);

        // Send email
        int id = accountEntity.getId();
        String body = "<h1>Verify your email address</h1>\n" +
                "<p>To continue setting up your Azure account, please verify that this is your email address.</p>\n" +
                "<a href=http://localhost:8080/Azure-Hotel/activate?token=" + encodedString + " class=\"btn btn-primary\" type=\"button\">Verify email address</a>\n" +
                "<p>Best regards,<br>The Azure Hotel team</p>";
        sendEmail(email, "Azure Hotel -Signup new account", body);
        model.addAttribute("accountEntity", accountEntity);
        model.addAttribute("success_msg", "Success! Please Verify your email before log in!");
        return "login";
    }

    @GetMapping("/activate")
    public String activate(@RequestParam("token") String token, Model model) {

        AccountEntity accountEntity = accountService.findByToken(token);
        if (accountEntity != null) {
            accountEntity.setStatus(UserStatus.ACTIVE);
            accountEntity.setToken("");

            // New Cart
            BookingCartEntity bookingCart = new BookingCartEntity();
            accountEntity.setBookingCartEntity(bookingCart);
            bookingCart.setAccountEntity(accountEntity);

            accountService.save(accountEntity);
            return "login";
        } else {
            return "not_found";
        }
    }

//    public void sendEmail(String to, String subject, String content) {
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(to);
//        mailMessage.setSubject(subject);
//        mailMessage.setText(content);
//
//        mailSender.send(mailMessage);
//    }

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
}
