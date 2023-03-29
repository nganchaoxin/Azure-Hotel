package mvc.controller;

import mvc.entity.*;
import mvc.repository.BookingCartItemRepository;
import mvc.repository.BookingCartRepository;
import mvc.repository.CategoryRepository;
import mvc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Controller
public class HotelReservationController {
    @Autowired
    RoomService roomService;

    @Autowired
    AccountService accountService;

    @Autowired
    BookingCartService bookingCartService;

    @Autowired
    BookingCartItemService bookingCartItemService;

    @Autowired
    BookingCartRepository bookingCartRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;
    @Autowired
    BookingCartItemRepository bookingCartItemRepository;

    @RequestMapping("/")
    public String index(Model model) {
        List<CategoryEntity> categoryList = categoryService.findAllCategory();
        model.addAttribute("categoryList", categoryList);
        return "index";
    }

    @RequestMapping(value = "/availableRoom", method = RequestMethod.GET)
    public String showAvailableRoom(
                                    @RequestParam("checkin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkin,
                                    @RequestParam("checkout") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkout,
                                    @RequestParam("roomType") String roomType,
                                    @RequestParam("guests") int guests,
                                    Model model,
                                    HttpSession session) {
        if (checkout.compareTo(checkin) < 0) {
            return "notFound";
        }
        List<RoomEntity> availableRoomList = roomService.getAvailableRooms(checkin, checkout, roomType, guests);
        session.setAttribute("check_in", checkin);
        session.setAttribute("check_out", checkout);
        model.addAttribute("availableRoomList", availableRoomList);

        return "available_rooms";
    }

    @RequestMapping(value = "/addToCart/room={roomId}", method = RequestMethod.GET)
    public String addToCart(Model model, @PathVariable int roomId, HttpSession session, HttpServletRequest request) {
        AccountEntity account = (AccountEntity) session.getAttribute("accountEntity");
        BookingCartEntity bookingCart = bookingCartService.findById(account.getId());

        List<BookingCartItemEntity> cartItemSessionList = (List<BookingCartItemEntity>) request.getSession().getAttribute("cartItemList");
        List<BookingCartItemEntity> bookingCartItemDatabase = bookingCartItemService.findAllByBookingCartId(bookingCart.getId());

        RoomEntity room = roomService.findRoomById(roomId);

        BookingCartItemEntity cartItem = new BookingCartItemEntity();
        cartItem.setCheck_in((Date) session.getAttribute("check_in"));
        cartItem.setCheck_out((Date) session.getAttribute("check_out"));
        cartItem.setRoomEntity(room);
        cartItem.setBookingCartEntity(bookingCart);

        // Init
        if (cartItemSessionList == null) {
            cartItemSessionList = new ArrayList<>();

            request.getSession().setAttribute("cartItemList", cartItemSessionList);
            return "redirect:/bookingcart";
        } else {
            int indexSession = this.exists(roomId, cartItemSessionList);
            if (indexSession == -1) {
                cartItemSessionList.add(cartItem);
            } else {
                return "redirect:/bookingcart";
            }
            request.getSession().setAttribute("cartItemList", cartItemSessionList);
        }
        bookingCartItemService.deleteAll(bookingCartItemDatabase);
        bookingCartItemService.saveAll(cartItemSessionList);

        model.addAttribute("cartItemList", cartItemSessionList);
        return "redirect:/bookingcart";
    }

    private int exists(int roomId, List<BookingCartItemEntity> cartItem) {
        for (int i = 0; i < cartItem.size(); i++) {
            if (cartItem.get(i).getRoomEntity().getId() == roomId) {
                return i;
            }
        }
        return -1;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}