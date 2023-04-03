package mvc.controller;

import mvc.entity.*;
import mvc.service.BookingCartItemService;
import mvc.service.BookingCartService;
import mvc.service.ImageService;
import mvc.service.RoomService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    RoomService roomService;

    @Autowired
    BookingCartService bookingCartService;

    @Autowired
    BookingCartItemService bookingCartItemService;

    @Autowired
    ImageService imageService;

    @RequestMapping(value = "/availableRoom", method = RequestMethod.GET)
    public String showAvailableRoom(
            HttpServletRequest request,
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

        List<ImageEntity> imageList = imageService.findAllImageByCategory(roomType);
        model.addAttribute("imageList", imageList);

        List<BookingCartItemEntity> cartItemSessionList = (List<BookingCartItemEntity>) request.getSession().getAttribute("cartItemList");
        request.getSession().setAttribute("cartItemList", cartItemSessionList);

        return "available_rooms";
    }

    @RequestMapping(value = "/getImagePhoto/{id}")
    public void getImagePhoto(HttpServletResponse response, @PathVariable("id") long id) throws Exception {
        response.setContentType("image/jpeg");

        ImageEntity i = imageService.findById(id);
        byte[] ph = i.getUrl();
        InputStream inputStream = new ByteArrayInputStream(ph);
        IOUtils.copy(inputStream, response.getOutputStream());
    }

    @RequestMapping(value = "/addToCart/room={roomId}", method = RequestMethod.GET)
    public String addToCart(Model model, @PathVariable int roomId, HttpSession session, HttpServletRequest request) {
        // Find account and booking cart
        AccountEntity account = (AccountEntity) session.getAttribute("accountEntity");
        if (account == null) {
            return "redirect:/login";
        }
        List<BookingCartEntity> bookingCartList = bookingCartService.findByAccountId(account.getId());
        RoomEntity room = roomService.findRoomById(roomId);

        List<BookingCartItemEntity> cartItemSessionList = (List<BookingCartItemEntity>) request.getSession().getAttribute("cartItemList");
        List<BookingCartItemEntity> bookingCartItemDatabase = bookingCartItemService.findAllByBookingCartId(bookingCartList.get(0).getId());

        // Set new cart item
        BookingCartItemEntity cartItem = new BookingCartItemEntity();
        cartItem.setCheck_in((Date) session.getAttribute("check_in"));
        cartItem.setCheck_out((Date) session.getAttribute("check_out"));
        cartItem.setRoomEntity(room);
        cartItem.setBookingCartEntity(bookingCartList.get(0));

        // Init
        if (cartItemSessionList == null) {
            cartItemSessionList = new ArrayList<>();

            request.getSession().setAttribute("cartItemList", cartItemSessionList);
            return "redirect:/bookingcart";
        } else {
            int indexSession = this.exists(roomId, cartItemSessionList);
            // If room not exist
            if (indexSession == -1) {
                cartItemSessionList.add(cartItem);
            } else {
                // If room exist
                return "redirect:/bookingcart";
            }
            request.getSession().setAttribute("cartItemList", cartItemSessionList);
        }
        // Delete all DB before add SS to DB
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