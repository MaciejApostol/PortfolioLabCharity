package pl.coderslab.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.CurrentUser;
import pl.coderslab.entity.Institution;
import pl.coderslab.service.DonationService;
import pl.coderslab.service.InstitutionService;

import java.util.List;


@Controller
public class HomeController {
    private final InstitutionService institutionService;
    private final DonationService donationService;

    public HomeController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @RequestMapping("/")
    public String homeAction(Model model) {
        List<Institution> institutions = institutionService.findAll();
        int countDonations = donationService.count(null);
        int sumBagsQuantity = donationService.sumBagsQuantity(null);
        model.addAttribute("institutions", institutions);
        model.addAttribute("countDonations", countDonations);
        model.addAttribute("sumBagsQuantity", sumBagsQuantity);
        return "index";
    }

}
