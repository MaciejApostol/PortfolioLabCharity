package pl.coderslab.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.entity.CurrentUser;
import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;

@ControllerAdvice
public class ControllerAdvisor {
    private final UserService userService;

    public ControllerAdvisor(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("loggedUser")
    public User loggedUser(@AuthenticationPrincipal CurrentUser currentUser) {
        if(currentUser == null){
            return null;
        }
        return userService.findByEmail(currentUser.getUsername());
    }
}
