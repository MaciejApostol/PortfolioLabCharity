package pl.coderslab.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.entity.*;
import pl.coderslab.service.CategoryService;
import pl.coderslab.service.DonationService;
import pl.coderslab.service.InstitutionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Secured({"ROLE_USER", "ROLE_ADMIN"})
public class DonationController {
    private final DonationService donationService;
    private final CategoryService categoryService;
    private final InstitutionService institutionService;

    public DonationController(DonationService donationService, CategoryService categoryService,
                              InstitutionService institutionService) {
        this.donationService = donationService;
        this.categoryService = categoryService;
        this.institutionService = institutionService;
    }

    @GetMapping("/show-donations")
    public String showDonations(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        List<Donation> donations = donationService.findAllByUser(currentUser.getUser());
        model.addAttribute("donations", donations);
        return "showDonations";
    }

    @RequestMapping("/donation-details")
    public String showDonationDetails(@RequestParam Long id, Model model) {
        Donation donation = donationService.findById(id);
        model.addAttribute("donation", donation);
        return "donationDetails";
    }

    @RequestMapping("/edit-donation")
    public String showDonationForm(@RequestParam(required = false) Long id, Model model) {
        List<Category> categories = categoryService.findAll();
        List<Institution> institutions = institutionService.findAll();
        Donation donation;
        if (id == null) {
            donation = new Donation();
        } else {
            donation = donationService.findById(id);
        }
        model.addAttribute("donation", donation);
        model.addAttribute("categories", categories);
        model.addAttribute("institutions", institutions);
        return "editDonation";
    }

    @PostMapping("/edit-donation")
    public String processDonationForm(@Valid Donation donation, BindingResult result, Model model) {
        model.addAttribute("donation", donation);
        if (result.hasErrors()) {
            return "editDonation";
        }
        donationService.save(donation);
        return "redirect:show-donations";
    }

    @RequestMapping("/delete-donation")
    public String deleteDonation(@RequestParam Long id) {
        donationService.deleteById(id);
        return "redirect:show-donations";
    }
}
