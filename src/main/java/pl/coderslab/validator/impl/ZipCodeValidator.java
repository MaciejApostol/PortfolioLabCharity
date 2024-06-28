package pl.coderslab.validator.impl;

import pl.coderslab.validator.ZipCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZipCodeValidator implements ConstraintValidator<ZipCode, String> {
    @Override
    public void initialize(ZipCode constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if ( value == null ) {
            return true;
        }
        Pattern compiledPattern = Pattern.compile("\\d{2}-\\d{3}");
        Matcher matcher = compiledPattern.matcher(value.replaceAll(" ", ""));
        return matcher.matches();
    }
}
