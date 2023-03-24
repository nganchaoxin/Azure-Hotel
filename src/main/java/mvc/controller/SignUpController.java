package mvc.controller;

import mvc.entity.AccountEntity;
import mvc.entity.RoleEntity;
import mvc.enums.UserStatus;
import mvc.repository.RoleRepository;
import mvc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

import static mvc.enums.Role.ROLE_USER;


@Controller
public class SignUpController {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    AccountService accountService;
    @Autowired
    RoleRepository roleRepository;
    @GetMapping("signup")
    public String signUpPage(Model model, @RequestParam(value = "error", required = false) boolean error) {
        if (error) {
            model.addAttribute("message", "Login Fail!!!");
        }
        model.addAttribute("newAccount", new AccountEntity());
        return "signup";

    }

    @PostMapping("signup")
    public  String signUp(@ModelAttribute(name = "newAccount") AccountEntity accountEntity) {
        // Encoder password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        accountEntity.setPassword(encoder.encode(accountEntity.getPassword()));

        RoleEntity role = new RoleEntity();
        role.setRole(ROLE_USER);
        role.setId(2);

//        RoleEntity role = roleRepository.getRoleUser();

        Set<RoleEntity> roles = new HashSet<>();
        roles.add(role);

        accountEntity.setUserRoles(roles);
        accountEntity.setStatus(UserStatus.ACTIVE);
        accountService.save(accountEntity);
        return "login";
    }
}
