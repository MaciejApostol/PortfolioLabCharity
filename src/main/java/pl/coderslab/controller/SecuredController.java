package pl.coderslab.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Secured("ROLE_ADMIN")
public class SecuredController {
    @GetMapping("/secured")
    @ResponseBody
    public String secured1(){
        return "secured";
    }
}
