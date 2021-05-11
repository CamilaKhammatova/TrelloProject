package ru.itis.khammatova.api.validations.annotations;

import ru.itis.khammatova.api.validations.validators.PasswordDifficultValidator;
import ru.itis.khammatova.api.validations.validators.PasswordsMatchingValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordDifficultValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordDifficult {
    String message() default "";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
