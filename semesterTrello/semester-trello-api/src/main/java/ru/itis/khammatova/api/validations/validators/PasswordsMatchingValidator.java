package ru.itis.khammatova.api.validations.validators;

import ru.itis.khammatova.api.dto.SignUpForm;
import ru.itis.khammatova.api.validations.annotations.PasswordsMatching;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsMatchingValidator implements ConstraintValidator<PasswordsMatching, SignUpForm> {

    @Override
    public boolean isValid(SignUpForm signUpForm, ConstraintValidatorContext constraintValidatorContext) {
        return signUpForm.getRepeatPassword().equals(signUpForm.getPassword());
    }
}
