package com.example.kanbanbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, code = HttpStatus.BAD_REQUEST, reason = "Account with such username exists!")
public class WrongUsernameException extends IllegalArgumentException {
    public WrongUsernameException(String message) {
        super("Account with such username exists!");
    }
}
