package mvc.controller;

import mvc.entity.BookingEntity;
import mvc.entity.CategoryEntity;
import mvc.entity.RoomEntity;
import mvc.enums.CategoryRoom;
import mvc.enums.RoomStatus;
import mvc.service.BookingService;
import mvc.service.CategoryService;
import mvc.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    RoomService roomService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    BookingService bookingService;

    // Show
    @RequestMapping(value = "/room", method = RequestMethod.GET)
    public String showRoom(Model model) {
        List<RoomEntity> roomList = roomService.findAllRoom();

        model.addAttribute("roomList", roomList);
        return "admin/room";
    }
    // Add
    @RequestMapping(value = "/addRoom", method = RequestMethod.GET)
    public String showAddRoom(Model model) {
        model.addAttribute("room", new RoomEntity());
        model.addAttribute("action", "addRoom");
        setCategoryDropDownList(model);
        setStatusDropDownList(model);

        return "admin/updateroom";
    }
    // Save room add
    @RequestMapping(value = "/addRoom", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String saveRoom(@Valid @ModelAttribute("room") RoomEntity room, BindingResult result, Model model) {
        if (result.hasErrors() || room.getCategoryEntity().getId() == 0) {
            model.addAttribute("type", "update");
            setCategoryDropDownList(model);
            setStatusDropDownList(model);

            if (room.getCategoryEntity().getId() == 0) {
                model.addAttribute("type", "update");
                model.addAttribute("message", "Plz input category");
            }
            return "admin/updateroom";
        }
        roomService.saveRoom(room);
        return "redirect:/admin/room";
    }

    // Edit
    @RequestMapping(value = "/editRoom/{roomId}", method = RequestMethod.GET)
    public String showEditRoom(Model model, @PathVariable int roomId) {
        model.addAttribute("room", roomService.findRoomById(roomId));
        model.addAttribute("msg", "Update room information");
        model.addAttribute("type", "update");
        model.addAttribute("action", "updateRoom");
        setCategoryDropDownList(model);
        setStatusDropDownList(model);

        if (roomService.findRoomById(roomId) != null) {
            return "admin/updateroom";
        } else {
            model.addAttribute("id", roomId);
        }

        return "notFound";
    }

    // Update
    @RequestMapping(value = "/editRoom/updateRoom", method = RequestMethod.POST)
    public String updateRoom(@Valid @ModelAttribute("room") RoomEntity room, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("type", "update");
            setCategoryDropDownList(model);
            setStatusDropDownList(model);

            return "admin/updateroom";
        }

        roomService.saveRoom(room);
        return "redirect:/admin/room";

    }

    // Delete
    @RequestMapping(value="/deleteRoom/{id}", method=RequestMethod.GET)
    public String deleteRoom(Model model, @PathVariable int id) {
        roomService.deleteById(id);
        return"redirect:/admin/room";

    }
    public void setCategoryDropDownList(Model model) {
        List<CategoryRoom> categoryRoomList = new ArrayList<>();
        categoryRoomList.add(CategoryRoom.STANDARD);
        categoryRoomList.add(CategoryRoom.LUXURY);
        categoryRoomList.add(CategoryRoom.BUSINESS);
        categoryRoomList.add(CategoryRoom.FAMILY);

        model.addAttribute("categoryRoomList", categoryRoomList);
    }

    public void setStatusDropDownList(Model model) {
        List<RoomStatus> roomStatusList = new ArrayList<>();
        roomStatusList.add(RoomStatus.AVAILABLE);
        roomStatusList.add(RoomStatus.OCCUPIED);

        model.addAttribute("roomStatusList", roomStatusList);
    }

    // CATEGORY
    // Show
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String showCategory(Model model) {
        List<CategoryEntity> categoryList = categoryService.findAllCategory();

        model.addAttribute("categoryList", categoryList);
        return "admin/category";
    }
    // Show add category
    @RequestMapping(value = "/addCategory", method = RequestMethod.GET)
    public String showAddCatgory(Model model) {
        model.addAttribute("category", new CategoryEntity());
        model.addAttribute("action", "addCategory");
        setCategoryDropDownList(model);

        return "admin/updatecategory";
    }

    // Save
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String saveCategory(@Valid @ModelAttribute("category") CategoryEntity category, BindingResult result, Model model) {
        if (result.hasErrors() ) {
            model.addAttribute("type", "update");
            setCategoryDropDownList(model);

            return "admin/updatecategory";
        }
       categoryService.save(category);
        return "redirect:/admin/category";
    }

    // Edit
    @RequestMapping(value = "/editCategory/{categoryId}", method = RequestMethod.GET)
    public String showEditCategory(Model model, @PathVariable int categoryId) {
        model.addAttribute("category", categoryService.findById(categoryId));
        model.addAttribute("msg", "Update category information");
        model.addAttribute("type", "update");
        model.addAttribute("action", "updateCategory");
        setCategoryDropDownList(model);
        setStatusDropDownList(model);

        if (categoryService.findById(categoryId) != null) {
            return "admin/updatecategory";
        } else {
            model.addAttribute("id", categoryId);
        }

        return "notFound";
    }
    // Update
    @RequestMapping(value = "/editCategory/updateCategory", method = RequestMethod.POST)
    public String updateCategory(@Valid @ModelAttribute("category") CategoryEntity category, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("type", "update");
            setCategoryDropDownList(model);

            return "admin/updateroom";
        }

        categoryService.save(category);
        return "redirect:/admin/category";

    }

    // Delete
    @RequestMapping(value="/deleteCategory/{id}", method=RequestMethod.GET)
    public String deleteCategory(Model model, @PathVariable int id) {
        categoryService.deleteById(id);
        return"redirect:/admin/category";

    }

    // BOOKING
    // Show
    @RequestMapping(value="/booking", method=RequestMethod.GET)
    public String showBooking(Model model){
        //List<BookingEntity> bookingList = bookingService.findAll();

       // model.addAttribute("bookingList",bookingList);
        return"admin/booking";
    }
    // IMAGE
    // Show
    @RequestMapping(value="/image", method=RequestMethod.GET)
    public String showImage(Model model){

        return"admin/image";
    }

}
