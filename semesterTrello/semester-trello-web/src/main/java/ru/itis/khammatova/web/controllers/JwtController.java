package ru.itis.khammatova.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.khammatova.api.dto.JwtSignInForm;
import ru.itis.khammatova.api.dto.JwtSignUpForm;
import ru.itis.khammatova.api.dto.UserDto;
import ru.itis.khammatova.api.services.JwtService;
import ru.itis.khammatova.api.services.UserService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/jwt")
public class JwtController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody JwtSignUpForm jwtSignUpForm) {
        if (userService.signUp(jwtSignUpForm)) {
            return "Registered successfully";
        }
        return "User with this email already exist";
    }

    @PostMapping("/auth")
    public String authenticateUser(@Valid @RequestBody JwtSignInForm loginRequest) {

        Optional<UserDto> userDto = userService.getUserByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

        if (userDto.isPresent()) {
            return jwtService.generateJwtToken(userDto.get().getEmail());
        }

        return "authentication failed";
    }
}
