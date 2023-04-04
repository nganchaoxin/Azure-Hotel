package mvc.controller;

import mvc.entity.*;
import mvc.service.BookingCartItemService;
import mvc.service.BookingCartService;
import mvc.service.ImageService;
import mvc.service.RoomService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "2") int pageSize,
            Model model,
            HttpSession session) {
        // Check checkin date < checkout date
        if (checkout.compareTo(checkin) < 0) {
            return "notFound";
        }

        // Pageable for room list
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<RoomEntity> availableRoomPage = roomService.getAvailableRooms(checkin, checkout, roomType, guests, pageRequest);

        session.setAttribute("check_in", checkin);
        session.setAttribute("check_out", checkout);
        model.addAttribute("availableRoomList", availableRoomPage.getContent());
        model.addAttribute("currentPage", availableRoomPage.getNumber());
        model.addAttribute("totalPages", availableRoomPage.getTotalPages());
        model.addAttribute("totalItems", availableRoomPage.getTotalElements());

        // List all image in category
        List<ImageEntity> imageList = imageService.findAllImageByCategory(roomType);
        model.addAttribute("imageList", imageList);

        // Booking cart noti
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

        Date check_in = (Date) session.getAttribute("check_in");
        Date check_out = (Date) session.getAttribute("check_out");
        // Set new cart item
        BookingCartItemEntity cartItem = new BookingCartItemEntity();
        cartItem.setCheck_in(check_in);
        cartItem.setCheck_out(check_out);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        int differenceInMillis = (int) (cartItem.getCheck_out().getTime() - cartItem.getCheck_in().getTime());
        int total_night = differenceInMillis / (1000 * 60 * 60 * 24);
        cartItem.setTotal_night(total_night);
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