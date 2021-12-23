package com.example.kanbanbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "NotEnoughAmountOfResource")
public class NotEnoughResourceAmount extends IllegalArgumentException{
    public NotEnoughResourceAmount(String message) { super(message); }
}
