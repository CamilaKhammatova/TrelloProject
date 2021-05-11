package ru.itis.khammatova.web.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.khammatova.api.services.UserService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
public class UsesCountRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/users/count")
    public Integer userCount() {
        return userService.usersCount();
    }
}
