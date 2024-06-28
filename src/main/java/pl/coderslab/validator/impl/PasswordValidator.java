package pl.coderslab.validator.impl;

import pl.coderslab.validator.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public void initialize(Password constraintAnnotation) {
    }

    public static boolean matchNotFound(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return !matcher.find();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintContext) {
        List<String> errors = new ArrayList<>(List.of("Hasło musi mieć"));
        int len = 8;

        if (value == null || value.equals("")) {
            return true;
        }
        if (value.length() < len) {
            errors.add(String.format("przynajmniej %s znaki", len));
        }

        if (matchNotFound("\\p{Lu}", value)) {
            errors.add("przynajmniej jedną dużą literę");
        }
        if (matchNotFound("\\p{Ll}", value)) {
            errors.add("przynajmniej jedną małą literę");
        }
        if (matchNotFound("[!@#$%^&*()_\\-+={}\\[\\]|:;'`\"<,>\\\\.?/]", value)) {
            errors.add("przynajmniej jeden znak specjalny");
        }
        if (matchNotFound("\\d", value)) {
            errors.add("przynajmniej jedną cyfrę");
        }
        if (errors.size() > 1) {
            StringBuilder sb = new StringBuilder(String.join(" ", errors.subList(0, 2)));
            if (errors.size() > 3) {
                sb.append(", ").append(String.join(", ", errors.subList(2, errors.size() - 1)));
            }
            if (errors.size() > 2) {
                sb.append(" oraz ").append(errors.get(errors.size() - 1));
            }
            sb.append(".");
            constraintContext.disableDefaultConstraintViolation();
            constraintContext.buildConstraintViolationWithTemplate(sb.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
