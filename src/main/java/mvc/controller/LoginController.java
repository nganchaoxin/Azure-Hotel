/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller;

import mvc.entity.AccountEntity;
import mvc.entity.BookingCartEntity;
import mvc.entity.BookingCartItemEntity;
import mvc.entity.CategoryEntity;
import mvc.service.AccountService;
import mvc.service.BookingCartItemService;
import mvc.service.BookingCartService;
import mvc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    BookingCartService bookingCartService;

    @Autowired
    AccountService accountService;

    @Autowired
    BookingCartItemService bookingCartItemService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/login")
    public String loginPage(Model model, @RequestParam(value = "error", required = false) boolean error) {
        if (error) {
            model.addAttribute("message", "Login failll");
        }

        return "login";
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String welcomePage(Model model, HttpSession session) {
        // Show category list
        List<CategoryEntity> categoryList = categoryService.findAllCategory();
        model.addAttribute("categoryList", categoryList);

        // Auth account
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
            session.setAttribute("userEmail", username);
        }
        AccountEntity accountEntity = accountService.findByEmail(username);

        // Create session
        if (accountEntity != null) {
            session.setAttribute("accountEntity", accountEntity);

            List<BookingCartEntity> bookingCartList = bookingCartService.findByAccountId(accountEntity.getId());
            List<BookingCartItemEntity> cartItemDatabaseList = null;

            if (bookingCartList != null && !bookingCartList.isEmpty()) {
                BookingCartEntity bookingCart = bookingCartList.get(0);
                List<BookingCartItemEntity> cartItemSessionList = (List<BookingCartItemEntity>) session.getAttribute("cartItemList");
                if (cartItemSessionList == null || cartItemSessionList.isEmpty()) {
                    cartItemDatabaseList = bookingCartItemService.findAllByBookingCartId(bookingCart.getId());
                    session.setAttribute("cartItemList", cartItemDatabaseList);
                }
            }

        }
        return "index";
    }

    @GetMapping(value = "/about")
    public String aboutPage() {
        return "aboutpage";
    }
}
