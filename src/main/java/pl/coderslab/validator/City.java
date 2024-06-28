package pl.coderslab.validator;

import pl.coderslab.validator.impl.CityValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CityValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface City {
    String message() default "Insert valid city name.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
