package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.User;
import pl.coderslab.service.RoleService;
import pl.coderslab.service.UserService;
import pl.coderslab.validator.groups.Registration;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {
    private final UserService userService;
    private final RoleService roleService;
    public static String username;

    public RegisterController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String processRegisterForm(@Validated({Registration.class}) User user, BindingResult result, Model model,
                                      HttpServletRequest request) {
        //        do walidatora
        model.addAttribute("user", user);
        boolean invalidFeedback = true;
        String email = user.getEmail();
        String password = user.getPassword();
        String confirmPassword = request.getParameter("password2");
        model.addAttribute("password2", confirmPassword);
        User existingUsername = userService.findByUsername(username);
        User existingEmail = userService.findByEmail(email);

        if (existingUsername != null) {
            model.addAttribute("usernameExists", "Nazwa użytkownika już istnieje.");
            invalidFeedback = false;
        }
        if (existingEmail != null) {
            model.addAttribute("emailExists", "Adres email już istnieje.");
            invalidFeedback = false;
        }
//        if (!password.equals(confirmPassword)) {
//            model.addAttribute("differentPasswords", "Hasła nie są takie same.");
//            invalidFeedback = false;
//        }
        if (result.hasErrors()) {
            invalidFeedback = false;
        }
        if (!invalidFeedback) {
            return "register";
        }
        roleService.saveUserRole();
        userService.saveUser(user);
        return "redirect:";
    }
}
