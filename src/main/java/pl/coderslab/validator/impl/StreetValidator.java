package pl.coderslab.validator.impl;

import pl.coderslab.validator.Street;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StreetValidator implements ConstraintValidator<Street, String> {
    @Override
    public void initialize(Street constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        Pattern compiledPattern = Pattern.compile(
                "\\d{1,4} \\p{Lu}*\\p{Ll}+" +   //np. 3 Maja
                        "|" +
                        "(\\p{Lu}\\p{Ll}+((( )|-)\\p{Lu}\\p{Ll}+)*)" +    //np. Jana Nowaka-Jeziorańskiego
                        " \\d{1,4}(/\\d{1,4})*(\\p{Lu}|\\p{Ll})*( m\\.\\d{1,4})*");   //np. Długa 8/9A m.11
        Matcher matcher = compiledPattern.matcher(value);
        return matcher.matches();
    }
}
