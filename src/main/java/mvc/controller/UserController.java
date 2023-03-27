package mvc.controller;

import mvc.entity.AccountEntity;
import mvc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/account", method = GET)
    public String userHome(Model model, HttpSession session) {
        AccountEntity accountEntity = (AccountEntity) session.getAttribute("accountEntity");
        model.addAttribute("accountEntity", accountEntity);
        return "user/account";
    }

    @RequestMapping(value = "/account", method = POST, produces = "text/plain;charset=UTF-8")
    public String saveUser(@RequestParam(name="first_name") String first_name,
                           @RequestParam(name="last_name") String last_name,
                           @RequestParam(name="address") String address,
                           @RequestParam(name="username") String username,
                           @RequestParam(name="phone_number") String phone_number,
                           @RequestParam(name="gender") String gender,
                           @RequestParam(name ="birth_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birth_date,
                           HttpSession session) {
        AccountEntity accountEntity = (AccountEntity) session.getAttribute("accountEntity");
        accountEntity.setFirst_name(first_name);
        accountEntity.setLast_name(last_name);
        accountEntity.setAddress(address);
        accountEntity.setUsername(username);
        accountEntity.setPhone_number(phone_number);
        accountEntity.setGender(gender);
        accountEntity.setBirth_date(birth_date);
        accountService.save(accountEntity);
        return "user/account";
    }

    @RequestMapping("/forgotpassword")
    public String forgotPasword() {
        return "user/forgot_password";
    }

    @RequestMapping("/cardpayment")
    public String paymentCard() {
        return "user/payment_card";
    }

    @RequestMapping("/paymenthistory")
    public String paymentHistory() {
        return "user/payment_history";
    }

    @RequestMapping("/booking")
    public String booking() {
        return "user/booking";
    }

    @RequestMapping("/bookingdetail")
    public String bookingDetail() {
        return "user/booking_detail";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

}
