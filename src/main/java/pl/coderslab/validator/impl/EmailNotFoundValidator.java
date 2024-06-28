package pl.coderslab.validator.impl;

import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;
import pl.coderslab.validator.EmailNotFound;
import pl.coderslab.validator.Street;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailNotFoundValidator implements ConstraintValidator<EmailNotFound, String> {
    private final UserService userService;

    public EmailNotFoundValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(EmailNotFound constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        User user = userService.findByEmail(value);
        return user != null;
    }
}
