package ru.itis.khammatova.web.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.khammatova.web.security.details.UserDetailsImpl;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(HttpSession session, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (session.getAttribute("user") == null && userDetails != null) {
            session.setAttribute("user", userDetails.getUser());
        }
        return "index";
    }
}
