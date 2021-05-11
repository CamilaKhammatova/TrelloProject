package ru.itis.khammatova.web.controllers.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.itis.khammatova.api.dto.UserDto;
import ru.itis.khammatova.api.dto.UserRestForm;
import ru.itis.khammatova.api.services.UserService;
import ru.itis.khammatova.web.exceptions.UserEmailBadRequestException;
import ru.itis.khammatova.web.exceptions.UserNotFoundException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Get user by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = UserDto.class)})
    @GetMapping("/{id}")
    public UserDto userById(@PathVariable Long id) throws UserEmailBadRequestException {
        return userService.userById(id)
                .orElseThrow(() -> new UserEmailBadRequestException("User not found"));
    }

    @ApiOperation(value = "Save user")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = UserDto.class)})
    @PostMapping
    public UserDto saveUser(@Valid @RequestBody UserRestForm userRestForm) throws UserEmailBadRequestException {
        return userService.signUp(userRestForm)
                .orElseThrow(() -> new UserEmailBadRequestException("user with this email already exist"));
    }

    @ApiOperation(value = "Update user by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = UserDto.class)})
    @PutMapping("/{id}")
    public UserDto updateUser(@Valid @RequestBody UserRestForm userRestForm, @PathVariable Long id) throws UserNotFoundException {
        return userService.update(userRestForm, id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @ApiOperation(value = "Delete user by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "")})
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
