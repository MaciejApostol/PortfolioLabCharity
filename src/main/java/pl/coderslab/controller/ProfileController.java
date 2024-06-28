package pl.coderslab.controller;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.WebAppConfig;
import pl.coderslab.entity.CurrentUser;
import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;
import pl.coderslab.validator.groups.EditUser;

import javax.validation.Constraint;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.groups.Default;
import java.util.Set;


@Controller
@Secured("ROLE_USER")
public class ProfileController {
    private final UserService userService;
    private final Validator validator;

    public ProfileController(UserService userService, Validator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @RequestMapping("/edit-my-profile")
    public String showEditMyProfile(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        User user = currentUser.getUser();
        user.setPassword(null);
        model.addAttribute("user", user);
        return "editMyProfile";
    }

    @PostMapping("/edit-my-profile")
    public String processEditMyProfile(@Validated({EditUser.class}) User user, BindingResult result, Model model) {
        model.addAttribute("user", user);
        System.out.println();
        if (result.hasErrors()) {
            return "editMyProfile";
        }
        return "welcomePage";
    }

}
