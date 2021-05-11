package ru.itis.khammatova.api.services;

import ru.itis.khammatova.api.dto.JwtSignUpForm;
import ru.itis.khammatova.api.dto.SignUpForm;
import ru.itis.khammatova.api.dto.UserDto;
import ru.itis.khammatova.api.dto.UserRestForm;

import java.util.Optional;

public interface UserService {
    boolean signUp(SignUpForm signUpForm);

    Optional<UserDto> signUp(UserRestForm userRestForm);

    Optional<UserDto> userByEmail(String email);

    Optional<UserDto> userById(Long id);

    Optional<UserDto> update(UserRestForm userRestForm, Long id);

    void delete(Long id);

    boolean signUp(JwtSignUpForm jwtSignUpForm);

    Optional<UserDto> getUserByEmailAndPassword(String email, String password);

    Integer usersCount();
}
