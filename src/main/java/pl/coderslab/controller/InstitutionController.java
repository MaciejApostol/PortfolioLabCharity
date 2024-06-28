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
import pl.coderslab.entity.CurrentUser;
import pl.coderslab.entity.Institution;
import pl.coderslab.entity.User;
import pl.coderslab.service.InstitutionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Secured("ROLE_ADMIN")
public class InstitutionController {
    private final InstitutionService institutionService;

    public InstitutionController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @GetMapping("/show-institutions")
    public String showInstitutions(Model model) {
        List<Institution> institutions = institutionService.findAll();
        model.addAttribute("institutions", institutions);
        return "showInstitutions";
    }

    @RequestMapping("/edit-institution")
    public String showInstitutionForm(@RequestParam(required = false) Long id, Model model) {
        Institution institution;
        if (id == null) {
            institution = new Institution();
        } else {
            institution = institutionService.findById(id);
        }
        model.addAttribute("institution", institution);
        return "editInstitution";
    }

    @PostMapping("/edit-institution")
    public String processInstitutionForm(@Valid Institution institution, BindingResult result, Model model) {
        model.addAttribute("institution", institution);
        if (result.hasErrors()) {
            return "editInstitution";
        }
        institutionService.save(institution);
        return "redirect:show-institutions";
    }

    @RequestMapping("/delete-institution")
    public String deleteInstitution(@RequestParam Long id) {
        institutionService.deleteById(id);
        return "redirect:show-institutions";
    }

}
