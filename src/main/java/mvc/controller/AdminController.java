package mvc.controller;

import mvc.entity.CategoryEntity;
import mvc.entity.RoomEntity;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    RoomService roomService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/adminHotel", method = RequestMethod.GET)
    public String showAdminPage(Model model) {
        List<RoomEntity> roomList = roomService.findAllRoom();
        model.addAttribute("roomList", roomList);
        return "admin/adminpage";
    }

    @RequestMapping(value = "/addRoom", method = RequestMethod.GET)
    public String showAddRoom(Model model) {
        model.addAttribute("room", new RoomEntity());
        model.addAttribute("action", "addRoom");
        setCategoryDropDownList(model);
        return "admin/updateroom";

    }

    @RequestMapping(value = "/addRoom", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String saveRoom(@Valid @ModelAttribute("room") RoomEntity room, BindingResult result, Model model) {
        if (result.hasErrors() || room.getCategoryEntity().getId() == 0) {
            model.addAttribute("type", "update");
            setCategoryDropDownList(model);
            if (room.getCategoryEntity().getId() == 0) {
                model.addAttribute("type", "update");
                model.addAttribute("message", "Plz input category");
            }
            return "admin/updateroom";
        }
        roomService.saveRoom(room);
        return "redirect:/adminHotel";
    }

    // Edit
    @RequestMapping(value = "/editRoom/{roomId}", method = RequestMethod.GET)
    public String showEditRoom(Model model, @PathVariable int roomId) {
        model.addAttribute("room", roomService.findRoomById(roomId));
        model.addAttribute("msg", "Update room information");
        model.addAttribute("type", "update");
        model.addAttribute("action", "updateRoom");
        setCategoryDropDownList(model);

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

        if (result.hasErrors() || room.getCategoryEntity().getId() == 0) {
            model.addAttribute("type", "update");
            setCategoryDropDownList(model);
            if (room.getCategoryEntity().getId() == 0) {
                model.addAttribute("type", "update");
                model.addAttribute("message", "Plz input category");
            }
            return "admin/updateroom";
        }

        roomService.saveRoom(room);
        return "redirect:/adminHotel";
    }

    private void setCategoryDropDownList(Model model) {
        List<CategoryEntity> categoryList = categoryService.findAllCategory();

        if (!categoryList.isEmpty()) {
            Map<Integer, String> categoryMap = new HashMap<>();
            for (CategoryEntity categoryEntity : categoryList) {
                categoryMap.put(categoryEntity.getId(), categoryEntity.getCategory_name());
            }
            model.addAttribute("categoryList", categoryMap);
        }
    }

}
