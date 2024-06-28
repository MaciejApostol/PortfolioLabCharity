package pl.coderslab.validator.impl;

import org.springframework.context.annotation.Bean;
import pl.coderslab.entity.TokenAvailability;
import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;
import pl.coderslab.validator.Token;

import javax.persistence.EnumType;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class TokenValidator implements ConstraintValidator<Token, String> {
    private final UserService userService;

    public TokenValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(Token constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        User user = userService.findByToken(value);
        if (user == null) {
            return false;
        }
        if (user.getTokenAvailability() == TokenAvailability.UNAVAILABLE) {
            return false;
        }
        return user.getTokenDate().plusSeconds(60).isAfter(LocalDateTime.now());
//        return user.getTokenDate().plusDays(2).isAfter(LocalDateTime.now());
    }
}
