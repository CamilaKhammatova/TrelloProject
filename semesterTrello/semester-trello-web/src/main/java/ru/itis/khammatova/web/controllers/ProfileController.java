package ru.itis.khammatova.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.khammatova.api.dto.UserDto;
import ru.itis.khammatova.api.services.UserService;
import ru.itis.khammatova.web.exceptions.UserNotFoundException;
import ru.itis.khammatova.web.security.details.UserDetailsImpl;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String profile(HttpSession session, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (session.getAttribute("user") == null && userDetails != null) {
            session.setAttribute("user", userDetails.getUser());
        }
        return "profile";
    }

}
