package ru.itis.khammatova.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.khammatova.api.validations.annotations.PasswordDifficult;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRestForm {

    @NotBlank
    private String firstname;
    @Email
    private String email;
    @PasswordDifficult
    private String password;
}
