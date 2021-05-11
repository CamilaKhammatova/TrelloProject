package ru.itis.khammatova.api.validations.validators;

import ru.itis.khammatova.api.validations.annotations.PasswordDifficult;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordDifficultValidator implements ConstraintValidator<PasswordDifficult, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.matches("^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}$", password);
    }
}
