package pl.coderslab.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.CurrentUser;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UserAvailability;
import pl.coderslab.service.DonationService;
import pl.coderslab.service.UserService;

@Controller
public class LoginController {
    private final UserService userService;
    private final DonationService donationService;

    public LoginController(UserService userService, DonationService donationService) {
        this.userService = userService;
        this.donationService = donationService;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(Model model, User user) {
        model.addAttribute("user", user);
        User exisitingUser = userService.findByEmail(user.getEmail());
        if (exisitingUser != null) {
            UserAvailability availability = exisitingUser.getEnabled();
            if (availability == UserAvailability.ENABLED) {
                return "redirect:welcome-page";
            }
            model.addAttribute("enabled", exisitingUser.getEnabled());
        }
        return "login";
    }

        @Secured({"ROLE_USER", "ROLE_ADMIN"})
        @GetMapping("/welcome-page")
        public String showWelcomePage (@AuthenticationPrincipal CurrentUser currentUser, Model model){
            User user = currentUser.getUser();
            int countDonations = donationService.count(user);
            int sumBagsQuantity = donationService.sumBagsQuantity(user);
            model.addAttribute("user", user);
            model.addAttribute("countDonations", countDonations);
            model.addAttribute("sumBagsQuantity", sumBagsQuantity);
            return "welcomePage";
        }
    }
