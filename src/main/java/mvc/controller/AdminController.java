package mvc.controller;

import mvc.entity.*;
import mvc.enums.BookingStatus;
import mvc.enums.CategoryRoom;
import mvc.enums.RoomStatus;
import mvc.enums.UserStatus;
import mvc.repository.ImageRepository;
import mvc.service.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    RoomService roomService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    BookingService bookingService;

    @Autowired
    ImageService imageService;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    BookingDetailService bookingDetailService;
    @Autowired
    AccountService accountService;

    // ROOM
    // Show
    @RequestMapping(value = "/room", method = RequestMethod.GET)
    public String showRoom(Model model) {
        List<RoomEntity> roomList = roomService.findAllRoom();

        model.addAttribute("roomList", roomList);
        return "admin/room";
    }

    // Add
    @RequestMapping(value = "/addRoom", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String showAddRoom(Model model) {
        model.addAttribute("room", new RoomEntity());
        model.addAttribute("action", "addRoom");
        setCategoryDropDownList(model);
        setStatusDropDownList(model);

        return "admin/update_room";
    }

    // Save
    @RequestMapping(value = "/addRoom", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String saveRoom(@Valid @ModelAttribute("room") RoomEntity room, HttpSession session, BindingResult result, Model model) {
        if (result.hasErrors() || room.getRoom_name() == null || room.getRoom_number() == 0){
            setCategoryDropDownList(model);
            setStatusDropDownList(model);

            model.addAttribute("message", "Please fill out this field");
            return "admin/update_room";
        }

        List<RoomEntity> roomList = roomService.findAllRoom();

        for (RoomEntity r : roomList) {
            if (result.hasErrors() || room.getRoom_name().equals(r.getRoom_name()) || room.getRoom_number() == r.getRoom_number()) {
                setCategoryDropDownList(model);
                setStatusDropDownList(model);

                model.addAttribute("error_duplicate", "This name has already been used. Please choose a different input.");
                return "admin/update_room";
            }
        }
        session.setAttribute("msgAdd", "Room added successfully!");
        roomService.saveRoom(room);
        return "redirect:/admin/room";
    }

    // Edit
    @RequestMapping(value = "/editRoom/{roomId}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String showEditRoom(Model model, @PathVariable int roomId) {
        model.addAttribute("room", roomService.findRoomById(roomId));
        model.addAttribute("msg", "Update room information");
        model.addAttribute("type", "update");
        model.addAttribute("action", "updateRoom");
        setCategoryDropDownList(model);
        setStatusDropDownList(model);

        if (roomService.findRoomById(roomId) != null) {
            return "admin/update_room";
        } else {
            model.addAttribute("id", roomId);
        }

        return "not_found";
    }

    // Update
    @RequestMapping(value = "/editRoom/updateRoom", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String updateRoom(@Valid @ModelAttribute("room") RoomEntity room,HttpSession session, BindingResult result, Model model, @RequestParam(name="categoryId") int categoryId) {
        session.setAttribute("msgUpdate", "Room updated successfully!");
        room.setCategoryEntity(categoryService.findById(categoryId));
        roomService.saveRoom(room);
        return "redirect:/admin/room";

    }

    // Delete
    @RequestMapping(value = "/deleteRoom/{id}", method = RequestMethod.GET)
    public String deleteRoom(Model model, HttpSession session,@PathVariable int id) {
        roomService.deleteById(id);

        session.setAttribute("msgDelete", "Room deleted successfully! " + "ID room: " + id);
        return "redirect:/admin/room";
    }


    // CATEGORY
    // Show
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String showCategory(Model model) {
        List<CategoryEntity> categoryList = categoryService.findAllCategory();

        model.addAttribute("categoryList", categoryList);
        return "admin/category";
    }

    // Add
    @RequestMapping(value = "/addCategory", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String showAddCatgory(Model model) {
        model.addAttribute("category", new CategoryEntity());
        model.addAttribute("action", "addCategory");
        setCategoryDropDownList(model);

        return "admin/update_category";
    }

    // Save
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String saveCategory(@Valid @ModelAttribute("category") CategoryEntity category, BindingResult result, Model model) {
        if (result.hasErrors() || category.getPrice() == 0){
            setCategoryDropDownList(model);
            setStatusDropDownList(model);

            model.addAttribute("message", "Please fill out this field");
            return "admin/update_category";
        }
        List<CategoryEntity> categoryList = categoryService.findAllCategory();

        for (CategoryEntity c : categoryList) {
            if (result.hasErrors() || category.getCategory_name().equals(c.getCategory_name())) {
                setCategoryDropDownList(model);
                setStatusDropDownList(model);

                model.addAttribute("error_duplicate", "This name has already been used. Please choose a different input.");
                return "admin/update_category";
            }
        }
        categoryService.save(category);
        return "redirect:/admin/category";
    }

    // Edit
    @RequestMapping(value = "/editCategory/{categoryId}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String showEditCategory(Model model, @PathVariable int categoryId) {
        model.addAttribute("category", categoryService.findById(categoryId));
        model.addAttribute("msg", "Update category information");
        model.addAttribute("type", "update");
        model.addAttribute("action", "updateCategory");
        setCategoryDropDownList(model);
        setStatusDropDownList(model);

        if (categoryService.findById(categoryId) != null) {
            return "admin/update_category";
        } else {
            model.addAttribute("id", categoryId);
        }

        return "not_found";
    }

    // Update
    @RequestMapping(value = "/editCategory/updateCategory", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String updateCategory(@Valid @ModelAttribute("category") CategoryEntity category, BindingResult result, Model model) {
        categoryService.save(category);
        return "redirect:/admin/category";
    }

    // Delete
    @RequestMapping(value = "/deleteCategory/{id}", method = RequestMethod.GET)
    public String deleteCategory(Model model, @PathVariable int id) {
        categoryService.deleteById(id);
        return "redirect:/admin/category";

    }

    // BOOKING
    // Show booking
    @RequestMapping(value = "/booking", method = RequestMethod.GET)
    public String showBooking(Model model) {
        List<BookingEntity> bookingList = bookingService.findAll();

        model.addAttribute("bookingList", bookingList);
        return "admin/booking";
    }


    // View booking detail
    @RequestMapping(value = "/viewBookingDetail/{id}", method = RequestMethod.GET)
    public String viewBookingDetail(Model model, @PathVariable(name = "id") int id) {
        List<BookingDetailEntity> bookingDetailList = bookingDetailService.findByBookingId(id);
        model.addAttribute("bookingDetailList", bookingDetailList);

        return "admin/booking_detail";
    }

    // Cancel booking
    @RequestMapping(value = "/cancelBooking/{id}", method = RequestMethod.GET)
    public String cancelBooking(HttpSession session, Model model, @PathVariable(name = "id") int id) {
        BookingEntity bookingEntity = bookingService.findById(id);
        bookingEntity.setBooking_status(BookingStatus.CANCEL);
        bookingService.save(bookingEntity);

        session.setAttribute("msg", "Cancel Booking Successfully!");
        return "redirect:/admin/booking";
    }

    // IMAGE
    // Show
    @RequestMapping(value = "/image")
    public String showImage(Model model) {
        List<ImageEntity> imageList = imageService.findAll();
        model.addAttribute("imageList", imageList);
        return "admin/image";
    }

    // Add
    @RequestMapping(value = "/showImageForm", method = RequestMethod.GET)
    public String showAddImage(Model model) {
        setCategoryDropDownList(model);

        return "admin/update_image";
    }

    // Save
    @RequestMapping(value = "/addImage",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String saveImage(
            Model model,
            @ModelAttribute("image") ImageEntity image,
            @RequestParam("image_name") String image_name,
            @RequestPart("image_url") MultipartFile image_url,
            @RequestParam("category_name") String category_name) throws IOException {

        ImageEntity i = new ImageEntity();
        i.setImage_name(image_name);
        i.setUrl(image_url.getBytes());

        CategoryEntity category = categoryService.findByCategoryName(category_name);
        i.setCategoryEntity(category);
        imageRepository.save(i);

        setCategoryDropDownList(model);
        return "redirect:/admin/image";

    }


    // Get image to table
    @RequestMapping(value = "/getImagePhoto/{id}")
    public void getImagePhoto(HttpServletResponse response, @PathVariable("id") long id) throws Exception {
        response.setContentType("image/jpeg");

        ImageEntity i = imageService.findById(id);
        byte[] ph = i.getUrl();
        InputStream inputStream = new ByteArrayInputStream(ph);
        IOUtils.copy(inputStream, response.getOutputStream());
    }

    @RequestMapping(value = "/editImage/{imageId}", method = RequestMethod.GET)
    public String showEditImage(Model model, @PathVariable long imageId) {
        ImageEntity image = imageService.findById(imageId);
        if (image != null) {
            model.addAttribute("image", image);
            model.addAttribute("category_name", image.getCategoryEntity().getCategory_name());

            model.addAttribute("msg", "Update image information");
            model.addAttribute("type", "update");
            model.addAttribute("action", "updateImage" + imageId);
            setCategoryDropDownList(model);
            return "admin/edit_image";
        } else {
            model.addAttribute("id", imageId);
            return "not_found";
        }
    }

    @RequestMapping(value = "/editImage/updateImage/{id}", method = RequestMethod.POST)
    public String editImage(@ModelAttribute("image") ImageEntity image,
                            @RequestParam("image_url") MultipartFile file,
                            @RequestParam("category_name") String categoryName,
                            @PathVariable long id,
                            Model model
    ) throws IOException {
        ImageEntity existingImage = imageService.findById(id);
        if (existingImage != null) {
            if (!file.isEmpty()) {
                existingImage.setUrl(file.getBytes());
            }
            existingImage.setImage_name(image.getImage_name());
            CategoryEntity category = categoryService.findByCategoryName(categoryName);
            existingImage.setCategoryEntity(category);
            imageRepository.save(existingImage);
            return "redirect:/admin/image";
        } else {
            return "not_found";
        }
    }

    // Delete
    @RequestMapping(value = "/deleteImage/{id}", method = RequestMethod.GET)
    public String deleteImage(Model model, @PathVariable long id) {
        imageService.deleteById(id);
        return "redirect:/admin/image";

    }

    // ACCOUNT
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String showAccount(Model model) {
        List<AccountEntity> accountList = accountService.findAll();

        model.addAttribute("accountList", accountList);
        return "admin/account";
    }

    @RequestMapping(value = "unactiveAccount/{id}", method = RequestMethod.GET)
    public String unactiveAccount(Model model, @PathVariable int id) {
        AccountEntity account = accountService.findById(id);
        account.setStatus(UserStatus.UNACTIVE);
        accountService.save(account);

        return "redirect:/admin/account";
    }

    @RequestMapping(value = "activeAccount/{id}", method = RequestMethod.GET)
    public String activeAccount(Model model, @PathVariable int id) {
        AccountEntity account = accountService.findById(id);
        account.setStatus(UserStatus.ACTIVE);
        accountService.save(account);

        return "redirect:/admin/account";
    }

    // DROP DOWN
    private void setCategoryDropDownList(Model model) {
        List<CategoryRoom> categoryRoomList = new ArrayList<>();
        categoryRoomList.add(CategoryRoom.STANDARD);
        categoryRoomList.add(CategoryRoom.LUXURY);
        categoryRoomList.add(CategoryRoom.BUSINESS);
        categoryRoomList.add(CategoryRoom.FAMILY);
        categoryRoomList.add(CategoryRoom.STUDIO);

        model.addAttribute("categoryRoomList", categoryRoomList);
        List<CategoryEntity> categoryList = categoryService.findAllCategory();
        Map<Integer, String> categoryMap = new HashMap<>();
        for (CategoryEntity categoryEntity : categoryList) {
            String categoryName = String.valueOf(categoryEntity.getCategory_name());
            categoryMap.put(categoryEntity.getId(), categoryName);
        }

        model.addAttribute("categoryList", categoryMap);

    }


    public void setStatusDropDownList(Model model) {
        List<RoomStatus> roomStatusList = new ArrayList<>();
        roomStatusList.add(RoomStatus.AVAILABLE);
        roomStatusList.add(RoomStatus.OCCUPIED);

        model.addAttribute("roomStatusList", roomStatusList);
    }

    // SEARCH
    @GetMapping("/search")
    public String search(@RequestParam(name = "query", required = false) String query, Model model, HttpSession session) {
        if (query == null || query.isEmpty() ) {
            session.setAttribute("nullBookingFound", "Please provide a search query.");
        } else {
            List<BookingEntity> resultList= bookingService.search(query);
            if (resultList.isEmpty()){
                session.setAttribute("findFail", "No booking found");
                return "admin/booking";
            }
            model.addAttribute("resultList", resultList);
            session.setAttribute("findSuccess", "All booking from search");
        }

        return "admin/booking";
    }

    // CHART
    @GetMapping("/chart")
    public String showChart(){
        return "admin/chart";
    }
    @RequestMapping(value = "total-price-data", method = RequestMethod.GET,  produces = "application/json")
    @ResponseBody
    public List<Double> getBookingTotalPriceData(HttpServletRequest request) {

        List<Double> totalPriceEachMonthList = new ArrayList<>();
        // create a map to store the total price for each month,loop to put month and total price
        Map<Integer, Double> monthToTotalPriceMap = new HashMap<>();

        for (BookingEntity booking : bookingService.findAll()) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(booking.getBooking_date());

            int month = calendar.get(Calendar.MONTH) + 1;
            double totalPriceEachMonth = booking.getTotal_price();

            // get all booking have the same month and sum up
            if (monthToTotalPriceMap.containsKey(month)) {
                totalPriceEachMonth += monthToTotalPriceMap.get(month);
            }
            monthToTotalPriceMap.put(month, totalPriceEachMonth);
        }

        // adding the total price of each month to the totalPriceEachMonth list.
        for (int i = 1; i <= 12; i++) {
            if (monthToTotalPriceMap.containsKey(i)) {
                totalPriceEachMonthList.add(monthToTotalPriceMap.get(i));
            } else {
                totalPriceEachMonthList.add(0.0);
            }
        }
        return totalPriceEachMonthList;
    }

}
