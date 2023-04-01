package mvc.controller;

import mvc.entity.AccountEntity;
import mvc.entity.BookingCartEntity;
import mvc.entity.RoleEntity;
import mvc.enums.UserStatus;
import mvc.repository.RoleRepository;
import mvc.service.AccountService;
import mvc.service.BookingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    RoleRepository roleRepository;
    @Autowired
    MailSender mailSender;
    @Autowired
    BookingCartService bookingCartService;
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
        if (!accountEntity.getPassword().equals(password_two) || accountTest != null) {
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
        sendEmail(email, "Azure Hotel -Signup new account", "This is your activation link: http://localhost:8080/Azure-Hotel/activate?token=" + encodedString);
        model.addAttribute("accountEntity", accountEntity);
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
            return "notFound";
        }
    }

    public void sendEmail(String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);

        mailSender.send(mailMessage);
    }
}
