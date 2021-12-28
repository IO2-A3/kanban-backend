package com.example.kanbanbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "InputDataException")
public class IncorrectInputDataException extends IllegalArgumentException{
    public IncorrectInputDataException(String message) { super(message); }
}
