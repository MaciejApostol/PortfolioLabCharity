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
import pl.coderslab.entity.UserAvailability;
import pl.coderslab.service.UserService;
import pl.coderslab.validator.groups.EditUser;

import java.util.List;

@Controller
@Secured("ROLE_ADMIN")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/show-users")
    public String showAdmins() {
        return "showUsers";
    }

    @RequestMapping("/edit-user")
    public String showUserForm(@RequestParam Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/edit-user")
    public String processUserForm(@Validated({EditUser.class}) User user, BindingResult result, Model model) {
        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "editUser";
        }
        userService.update(user);
        return "redirect:/show-users";
    }

    @RequestMapping("/block-user")
    public String blockUser(@RequestParam Long id) {
        userService.enableUser(id, UserAvailability.UNABLED);
        return "redirect:/show-users";
    }

    @RequestMapping("/unblock-user")
    public String unblockUser(@RequestParam Long id) {
        userService.enableUser(id, UserAvailability.ENABLED);
        return "redirect:/show-users";
    }

    @RequestMapping("/delete-user")
    public String deleteUser(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam Long id) {
        userService.deleteById(currentUser, id);
        return "redirect:/show-users";
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
