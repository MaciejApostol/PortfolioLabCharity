package pl.coderslab.validator;

import pl.coderslab.validator.impl.ZipCodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ZipCodeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ZipCode {
    String message() default "Insert valid zip code.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
