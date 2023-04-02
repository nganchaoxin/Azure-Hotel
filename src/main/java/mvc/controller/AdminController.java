package mvc.controller;

import mvc.entity.CategoryEntity;
import mvc.entity.ImageEntity;
import mvc.entity.RoomEntity;
import mvc.enums.RoomStatus;
import mvc.repository.ImageRepository;
import mvc.service.BookingService;
import mvc.service.CategoryService;
import mvc.service.ImageService;
import mvc.service.RoomService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
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

    @Autowired
    ImageService imageService;

    @Autowired
    ImageRepository imageRepository;

    // ROOM
    // Show
    @RequestMapping(value = "/room", method = RequestMethod.GET)
    public String showRoom(Model model) {
        List<RoomEntity> roomList = roomService.findAllRoom();

        model.addAttribute("roomList", roomList);
        return "admin/room";
    }

    // Add
    @RequestMapping(value = "/addRoom", method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    public String showAddRoom(Model model) {
        model.addAttribute("room", new RoomEntity());
        model.addAttribute("action", "addRoom");
        setCategoryDropDownList(model);
        setStatusDropDownList(model);

        return "admin/updateroom";
    }

    // Save
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
    @RequestMapping(value = "/editRoom/{roomId}", method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
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
    @RequestMapping(value = "/editRoom/updateRoom", method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
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
    @RequestMapping(value = "/deleteRoom/{id}", method = RequestMethod.GET)
    public String deleteRoom(Model model, @PathVariable int id) {
        roomService.deleteById(id);
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
    @RequestMapping(value = "/addCategory", method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    public String showAddCatgory(Model model) {
        model.addAttribute("category", new CategoryEntity());
        model.addAttribute("action", "addCategory");
        setCategoryDropDownList(model);

        return "admin/updatecategory";
    }

    // Save
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String saveCategory(@Valid @ModelAttribute("category") CategoryEntity category, BindingResult result, Model model) {
        if (result.hasErrors() || category.getCategory_name() == null) {
            model.addAttribute("type", "update");
            setCategoryDropDownList(model);

            if (category.getCategory_name() == null) {
                model.addAttribute("type", "update");
                model.addAttribute("message", "Plz input category");
            }

            return "admin/updatecategory";
        }
        categoryService.save(category);
        return "redirect:/admin/category";
    }
    // Edit
    @RequestMapping(value = "/editCategory/{categoryId}", method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
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
    @RequestMapping(value = "/editCategory/updateCategory", method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
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
    @RequestMapping(value = "/deleteCategory/{id}", method = RequestMethod.GET)
    public String deleteCategory(Model model, @PathVariable int id) {
        categoryService.deleteById(id);
        return "redirect:/admin/category";

    }

    // BOOKING
    // Show
    @RequestMapping(value = "/booking", method = RequestMethod.GET)
    public String showBooking(Model model) {
        //List<BookingEntity> bookingList = bookingService.findAll();

        // model.addAttribute("bookingList",bookingList);
        return "admin/booking";
    }

    // IMAGE
    // Show
    @RequestMapping(value = "/image")
    public ModelAndView showImage(ModelAndView model) {
        List<ImageEntity> imageList = imageService.findAll();
        model.addObject("imageList", imageList);
        model.setViewName("admin/image");
        return model;
    }

    // Add
    @RequestMapping(value = "/showImageForm", method = RequestMethod.GET)
    public String showAddImage(Model model) {
        setCategoryDropDownList(model);

        return "admin/updateimage";
    }

    // Save
    @RequestMapping(value = "/addImage",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView saveImage(
            Model model,
            @ModelAttribute("image") ImageEntity image,
            @RequestParam("image_name") String image_name,
            @RequestParam("image_type") String image_type,
            @RequestPart("image_url") MultipartFile image_url,
            @RequestParam("category_name") String category_name) {
        try {

            ImageEntity i = new ImageEntity();
            i.setImage_name(image_name);
            i.setImage_type(image_type);
            i.setUrl(image_url.getBytes());

            CategoryEntity category = categoryService.findByCategoryName(category_name);
            i.setCategoryEntity(category);
            imageRepository.save(i);

            setCategoryDropDownList(model);
            return new ModelAndView("redirect:/admin/image");

        } catch (Exception e) {
            return new ModelAndView("admin/image", "msg", "Error: " + e.getMessage());
        }
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

    // Edit
    @RequestMapping(value = "/editImage/{imageId}", method = RequestMethod.GET)
    public String showEditImage(Model model, @PathVariable int imageId) {
        model.addAttribute("image", imageService.findById(imageId));
        model.addAttribute("msg", "Update image information");
        model.addAttribute("type", "update");

        setCategoryDropDownList(model);

        if (imageService.findById(imageId) != null) {
            return "admin/updateimage";
        } else {
            model.addAttribute("id", imageId);
        }

        return "notFound";
    }

    // Delete
    @RequestMapping(value = "/deleteImage/{id}", method = RequestMethod.GET)
    public String deleteImage(Model model, @PathVariable long id) {
        imageService.deleteById(id);
        return "redirect:/admin/image";

    }

    // BOOKING


    // DROP DOWN
    private void setCategoryDropDownList(Model model) {
        List<CategoryEntity> categoryList = categoryService.findAllCategory();

        if (!categoryList.isEmpty()) {
            Map<Integer, String> categoryMap = new HashMap<>();
            for (CategoryEntity categoryEntity : categoryList) {
                String categoryName = String.valueOf(categoryEntity.getCategory_name());
                categoryMap.put(categoryEntity.getId(), categoryName);
            }

            model.addAttribute("categoryList", categoryMap);
        }
    }

    public void setStatusDropDownList(Model model) {
        List<RoomStatus> roomStatusList = new ArrayList<>();
        roomStatusList.add(RoomStatus.AVAILABLE);
        roomStatusList.add(RoomStatus.OCCUPIED);

        model.addAttribute("roomStatusList", roomStatusList);
    }
}
