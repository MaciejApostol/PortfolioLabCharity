package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;
import pl.coderslab.validator.groups.Authentication;
import pl.coderslab.validator.groups.NewPassword;
import pl.coderslab.validator.groups.PasswordReset;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PasswordResetController {
    private final UserService userService;

    public PasswordResetController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/password-reset")
    public String showPasswordResetForm(Model model) {
        model.addAttribute("user", new User());
        return "passwordReset";
    }

    @PostMapping("/password-reset")
    public String processPasswordResetForm(@Validated({PasswordReset.class}) User user, BindingResult result,
                                           Model model, HttpServletRequest request) {
        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "passwordReset";
        }
        userService.generateAndEmailToken(user, request);
        model.addAttribute("showEmailMessage", true);
        return "passwordReset";
    }

    @GetMapping("/email-authentication")
    public String showEmailConfirmationForm(@Validated(Authentication.class) User user, BindingResult result,
                                            Model model) {
        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "tokenUnavailable";
        }
        return "emailAuthentication";
    }

    @GetMapping("/new-password")
    public String showNewPasswordForm(@Validated(Authentication.class) User user, BindingResult result,
                                      Model model) {
        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "tokenUnavailable";
        }
        return "newPassword";
    }

    @PostMapping("/new-password")
    public String processNewPasswordForm(@Validated({NewPassword.class}) User user, BindingResult result,
                                         Model model) {
        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "newPassword";
        }
        userService.changePassword(user);
        return "redirect:/login";
    }
}