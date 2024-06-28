package pl.coderslab.validator.impl;

import pl.coderslab.validator.City;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CityValidator implements ConstraintValidator<City, String> {
    @Override
    public void initialize(City constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        Pattern compiledPattern = Pattern.compile("\\p{Lu}\\p{Ll}+((( )|-)\\p{Lu}\\p{Ll}+)*");
        Matcher matcher = compiledPattern.matcher(value);
        return matcher.matches();
    }
}
