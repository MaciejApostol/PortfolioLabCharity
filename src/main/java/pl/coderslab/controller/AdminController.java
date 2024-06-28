package pl.coderslab.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.CurrentUser;
import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;
import pl.coderslab.validator.groups.EditUser;
import pl.coderslab.validator.groups.NewAdmin;

import javax.validation.Valid;
import java.util.List;

@Controller
@Secured("ROLE_ADMIN")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/show-admins")
    public String showAdmins() {
        return "showAdmins";
    }

    @RequestMapping("/edit-admin")
    public String showEditAdminForm(@RequestParam Long id, Model model) {
        User admin = userService.findById(id);
        model.addAttribute("user", admin);
        return "editUser";
    }

    @PostMapping("/edit-admin")
    public String processEditAdminForm(@Valid User admin, BindingResult result, Model model) {
        model.addAttribute("user", admin);
        if (result.hasErrors()) {
            return "editUser";
        }
        userService.update(admin);
        return "redirect:/show-admins";
    }

    @RequestMapping("/delete-admin")
    public String deleteAdmin(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam Long id) {
        userService.deleteAdmin(currentUser, id);
        return "redirect:/show-admins";
    }

    @RequestMapping("/add-admin")
    public String showAddAdminForm(Model model) {
        model.addAttribute("admin", new User());
        return "addAdmin";
    }

    @PostMapping("/add-admin")
    public String processAddAdminForm(@Validated({NewAdmin.class}) User admin, BindingResult result,
                                      Model model) {
        model.addAttribute("admin", admin);
        if (result.hasErrors()) {
            return "addAdmin";
        }
        userService.addAdmin(admin);
        return "redirect:/show-admins";
    }

    @ModelAttribute("admins")
    public List<User> findAllAdmins() {
        return userService.findByRole("ROLE_ADMIN");
    }

    @ModelAttribute("users")
    public List<User> findAllUsers() {
        return userService.findByRole("ROLE_USER");
    }

    @ModelAttribute("currentUser")
    public User findCurrentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        return currentUser.getUser();
    }

}
