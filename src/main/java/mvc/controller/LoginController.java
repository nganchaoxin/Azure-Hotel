/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller;

import mvc.entity.*;
import mvc.service.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
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

    @Autowired
    ImageService imageService;

    @Autowired
    RatingService ratingService;

    @RequestMapping("/login")
    public String loginPage(Model model, @RequestParam(value = "error", required = false) boolean error) {
        if (error) {
            model.addAttribute("message", "Username or password is not correct!");
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

        List<RatingEntity> ratingEntityList = ratingService.findAll();
        model.addAttribute("ratingList", ratingEntityList);

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
            session.setAttribute("userEmail", username);
        }
        AccountEntity accountEntity = accountService.findByEmail(username);


        // Create session
        if (accountEntity != null) {
            session.setAttribute("accountEntity", accountEntity);
            Integer roomIdInSession = (Integer) session.getAttribute("roomIdToAddToCart");

            List<BookingCartEntity> bookingCartList = bookingCartService.findByAccountId(accountEntity.getId());
            List<BookingCartItemEntity> cartItemDatabaseList = null;

            if (bookingCartList != null && !bookingCartList.isEmpty()) {

                BookingCartEntity bookingCart = bookingCartList.get(0);
                List<BookingCartItemEntity> cartItemSessionList = (List<BookingCartItemEntity>) session.getAttribute("cartItemList");
                if (cartItemSessionList == null || cartItemSessionList.isEmpty()) {
                    cartItemDatabaseList = bookingCartItemService.findAllByBookingCartId(bookingCart.getId());
                    session.setAttribute("cartItemList", cartItemDatabaseList);
                    if(roomIdInSession != null) {
                        session.removeAttribute("roomIdToAddToCart");
                        int roomId = roomIdInSession.intValue();

                        return "redirect:/addToCart/room=" + roomId;
                    }
                }
            }

        }
        return "index";
    }

    @RequestMapping(value = "/getImagePhotoByCategory/{id}")
    public void getImagePhoto(HttpServletResponse response, @PathVariable long id) throws Exception {
        response.setContentType("image/jpeg");

        ImageEntity i = imageService.findByCategoryId(id);
        byte[] ph = i.getUrl();
        InputStream inputStream = new ByteArrayInputStream(ph);
        IOUtils.copy(inputStream, response.getOutputStream());
    }

    @GetMapping(value = "/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping(value = "/rating")
    public String rating(Model model) {
        model.addAttribute("rating", new RatingEntity());
        return "rating";
    }

    @PostMapping("/createrating")
    public String createRating(@ModelAttribute(name = "rating") RatingEntity rating,
                              @RequestParam(name = "content") String content,
                              @RequestParam(name = "hdrating") float hdrating,
                              HttpSession session) {
        AccountEntity accountEntity = (AccountEntity) session.getAttribute("accountEntity");
        rating.setReview_status("WAIT");
        rating.setReview_date(new Date());
        rating.setRating_point(hdrating);
        rating.setAccountEntity(accountEntity);
        rating.setContent(content);
        ratingService.save(rating);
        return "thankyou";
    }

    @GetMapping(value = "/restaurant")
    public String restaurantPage() {
        return "restaurant";
    }

    @GetMapping(value = "/rooms")
    public String roomPage(Model model) {
        // Show category list
        List<CategoryEntity> categoryList = categoryService.findAllCategory();
        model.addAttribute("categoryList", categoryList);

        return "rooms";
    }

    @RequestMapping(value = "/getuseravatar/{id}")
    public void getUserPhoto(HttpServletResponse response, @PathVariable("id") int id) throws Exception {
        response.setContentType("image/jpeg");
        AccountEntity accountEntity = accountService.findById(id);
        byte[] ph = accountEntity.getPhoto();
        InputStream inputStream = new ByteArrayInputStream(ph);
        IOUtils.copy(inputStream, response.getOutputStream());
    }
}
