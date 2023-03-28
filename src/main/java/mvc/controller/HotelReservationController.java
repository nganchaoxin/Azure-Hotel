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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
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
    public String showAvailableRoom(@RequestParam("checkin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkin, @RequestParam("checkout") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkout, @RequestParam("roomType") String roomType, @RequestParam("guests") int guests, Model model, HttpSession session) {
        List<RoomEntity> availableRoomList = roomService.getAvailableRooms(roomType, guests, checkin, checkout);
        session.setAttribute("check_in", checkin);
        session.setAttribute("check_out", checkout);
        model.addAttribute("availableRoomList", availableRoomList);
        return "available_rooms";
    }

//    @RequestMapping(value = "/myCart", method = RequestMethod.GET)
//    public String showBookingCart(Model model, @PathVariable int cartId, HttpSession session, HttpServletRequest request) {
//
//
//        List<BookingCartItemEntity> cartItemSessionList = (List<BookingCartItemEntity>) session.getAttribute("cartItemList");
//        List<BookingCartItemEntity> cartItemDatabaseList = bookingCartItemService.findAllByBookingCartId(cartId);
//
//        if (cartItemSessionList == null && cartItemDatabaseList == null) {
//            System.out.println("cart -> cartItemSessionList == null && cartItemDatabaseList == null");
//            cartItemSessionList = new ArrayList<>();
//            cartItemDatabaseList = new ArrayList<>(cartItemSessionList);
//
//            request.getSession().setAttribute("cartItemList", cartItemSessionList);
//        }
//
//        if (cartItemSessionList == null && cartItemDatabaseList != null) {
//            System.out.println("cart -> add DB list to SS list to load from session");
//            cartItemSessionList = new ArrayList<>();
//            cartItemSessionList.addAll(cartItemDatabaseList);
//
//            request.getSession().setAttribute("cartItemList", cartItemSessionList);
//
//        } else {
//            System.out.println("cart -> load from session");
//            request.getSession().setAttribute("cartItemList", cartItemSessionList);
//
//        }
//
//        model.addAttribute("cartItemList", cartItemSessionList);
//        return "booking_cart";
//    }

    @RequestMapping(value = "/addToCart/room={roomId}", method = RequestMethod.GET)
    public String addToCart(Model model, @PathVariable int roomId, HttpSession session, HttpServletRequest request) {
        List<BookingCartItemEntity> cartItemSessionList = (List<BookingCartItemEntity>) request.getSession().getAttribute("cartItemList");
        List<BookingCartItemEntity> cartItemDatabaseList = bookingCartItemService.findAllByBookingCartId(1);

        BookingCartEntity bookingCart = bookingCartService.findById(1);
        RoomEntity room = roomService.findRoomById(roomId);

        BookingCartItemEntity cartItem = new BookingCartItemEntity();
        cartItem.setCheck_in((Date) session.getAttribute("check_in"));
        cartItem.setCheck_out((Date) session.getAttribute("check_out"));
        cartItem.setRoomEntity(room);
        cartItem.setBookingCartEntity(bookingCart);

        // Init
        if (cartItemSessionList == null) {
            System.out.println("cartItemSessionList == null");
            cartItemSessionList = new ArrayList<>();
            request.getSession().setAttribute("cartItemList", cartItemSessionList);
            return "redirect:/bookingcart";
        } else {
            // Check if item exist in ss
            int indexSession = this.exists(roomId, cartItemSessionList);
            if (indexSession == -1) {
                cartItemSessionList.add(cartItem);

                for (BookingCartItemEntity b : cartItemSessionList) {
                    //bookingCartItemService.deleteByCartId(b.getCartID);

                }
                // save DB list in database by repo
                bookingCartItemRepository.saveAll(cartItemDatabaseList);
            } else {
                return "redirect:/bookingcart";
            }
        }

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