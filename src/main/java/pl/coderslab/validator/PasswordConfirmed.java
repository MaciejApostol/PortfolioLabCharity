package pl.coderslab.validator;

import pl.coderslab.validator.impl.PasswordConfirmedValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordConfirmedValidator.class)
@Target({ElementType.TYPE_USE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConfirmed {
    String message() default "Hasła nie są takie same.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
