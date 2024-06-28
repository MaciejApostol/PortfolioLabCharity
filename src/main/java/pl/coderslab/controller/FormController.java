package pl.coderslab.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Category;
import pl.coderslab.entity.CurrentUser;
import pl.coderslab.entity.Donation;
import pl.coderslab.entity.Institution;
import pl.coderslab.service.CategoryService;
import pl.coderslab.service.DonationService;
import pl.coderslab.service.EmailService;
import pl.coderslab.service.InstitutionService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FormController {
    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final EmailService emailService;

    public FormController(CategoryService categoryService, InstitutionService institutionService,
                          DonationService donationService, EmailService emailService) {
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.emailService = emailService;
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/form")
    public String addDonation(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        Donation donation = new Donation();
        donation.setUser(currentUser.getUser());
        model.addAttribute("donation", donation);
        return "form";
    }

    @PostMapping("/form")
    public String addDonation(@Valid Donation donation, BindingResult results, Model model) {
        model.addAttribute("donation", donation);
        if (results.hasErrors()) {
            return "form";
        }
        donationService.save(donation);
        String categories = donation.getCategories()
                .stream()
                .map(category -> " - " + category.getName())
                .collect(Collectors.joining(",\n"));
        emailService.sendEmailWithAttachment(
                donation.getUser().getEmail(),
                "Potwierdzenie zamówienia odbioru darowizny",
                String.format("""
                                Dziękujemy za przekazaną darowiznę.
                                Darowiznę przyjmuje %s,
                                Odajesz:
                                %s
                                Liczba worków: %s,
                                Adres odbioru:
                                    Ulica: %s,
                                    Kod pocztowy: %s %s,
                                Tremin odbioru
                                    Data: %s,
                                    Godzina: %s,
                                    Uwagi dla kuriera: %s.
                                """, donation.getInstitution().getName(), categories, donation.getQuantity(),
                        donation.getStreet(), donation.getZipCode(), donation.getCity(),
                        donation.getPickUpDate(), donation.getPickUpTime(), donation.getPickUpComment()),
                "/home/nitro/Documents/JavaProjects/PortfolioLabCharity/src/main/webapp/resources/images/header-bg.jpg");
        return "formConfirmation";
    }

    @ModelAttribute("categories")
    public List<Category> addCategories() {
        return categoryService.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> addInstitution() {
        return institutionService.findAll();
    }
}
