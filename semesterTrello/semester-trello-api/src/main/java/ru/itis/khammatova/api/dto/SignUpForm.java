package ru.itis.khammatova.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.khammatova.api.validations.annotations.PasswordDifficult;
import ru.itis.khammatova.api.validations.annotations.PasswordsMatching;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@PasswordsMatching
public class SignUpForm {
    @NotBlank
    private String firstname;
    @Email
    private String email;
    @PasswordDifficult
    private String password;
    private String repeatPassword;
}
