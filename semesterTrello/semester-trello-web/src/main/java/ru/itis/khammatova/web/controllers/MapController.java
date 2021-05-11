package ru.itis.khammatova.web.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.khammatova.web.security.details.UserDetailsImpl;

import javax.servlet.http.HttpSession;

@Controller
public class MapController {

    @GetMapping("/map")
    public String map(HttpSession session, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (session.getAttribute("user") == null && userDetails != null) {
            session.setAttribute("user", userDetails.getUser());
        }
        return "map";
    }
}
