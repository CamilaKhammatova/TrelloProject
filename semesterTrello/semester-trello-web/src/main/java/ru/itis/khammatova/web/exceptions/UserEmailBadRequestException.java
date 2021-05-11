package ru.itis.khammatova.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserEmailBadRequestException extends Exception {
    public UserEmailBadRequestException(String message) {
        super(message);
    }
}

