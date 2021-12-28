package com.example.kanbanbackend.authentication.models;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Token has expired")
public class ExpiredTokenException extends IllegalArgumentException {
    public ExpiredTokenException(String message) { super(message); }
}
