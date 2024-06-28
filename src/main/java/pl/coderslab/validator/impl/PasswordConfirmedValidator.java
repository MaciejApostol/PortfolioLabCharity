package pl.coderslab.validator.impl;

import pl.coderslab.entity.User;
import pl.coderslab.validator.PasswordConfirmed;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConfirmedValidator implements ConstraintValidator<PasswordConfirmed, User> {
    @Override
    public void initialize(PasswordConfirmed constraintAnnotation) {
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        if (user == null) {
            return true;
        }

       return user.getPasswordConfirmation().equals(user.getPassword());
    }
}
