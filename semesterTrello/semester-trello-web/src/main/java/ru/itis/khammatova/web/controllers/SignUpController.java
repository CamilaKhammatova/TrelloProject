package ru.itis.khammatova.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.khammatova.api.dto.SignUpForm;
import ru.itis.khammatova.api.services.UserService;

import javax.validation.Valid;
import java.util.Objects;

@Controller
public class SignUpController {

    @Autowired
    private UserService userService;

    @GetMapping("/signUp")
    public String signUp(Model model) {
        model.addAttribute("signUpForm", new SignUpForm());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid SignUpForm signUpForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error);
                if (Objects.requireNonNull(error.getCodes())[0].equals("PasswordsMatching.signUpForm")) {
                    model.addAttribute("matchingError", "Passwords don't match");
                } else if (Objects.requireNonNull(error.getCodes())[0].equals("PasswordDifficult.signUpForm.password")) {
                    model.addAttribute("difficultError", "Password too easy");
                }
            });

            model.addAttribute("signUpForm", signUpForm);
            return "signUp";
        }

        if (userService.signUp(signUpForm)) { return "redirect:/signIn"; }

        return "redirect:/signUp";

    }
}
