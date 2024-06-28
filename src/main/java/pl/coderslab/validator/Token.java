package pl.coderslab.validator;

import pl.coderslab.validator.impl.TokenValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TokenValidator.class)
@Target({ElementType.TYPE_USE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {
    String message() default "Link nie jest poprawny.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
