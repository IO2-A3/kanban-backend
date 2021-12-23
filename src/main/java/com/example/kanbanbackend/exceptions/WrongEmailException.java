package com.example.kanbanbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, code = HttpStatus.BAD_REQUEST, reason = "Account with such email exists!")
public class WrongEmailException extends IllegalArgumentException {
    public WrongEmailException(String message) {
        super("Account with such email exists!");
    }
}
