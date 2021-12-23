package com.example.kanbanbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Error")
public class IncorrectIdInputException extends IllegalArgumentException {
    public IncorrectIdInputException(String message) { super(message); }
}
