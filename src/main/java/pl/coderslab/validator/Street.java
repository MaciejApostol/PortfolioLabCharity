package pl.coderslab.validator;

import pl.coderslab.validator.impl.StreetValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = StreetValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Street {
    String message() default "Podaj poprawną nazwę ulicy np. Długa 8/9A m.11";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
