package com.example.kanbanbackend.email;

import com.example.kanbanbackend.exceptions.WrongEmailException;

public class EmailSenderValidator {
    public void validateEmail(String email) {
        if (!org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(email)) {
            throw new WrongEmailException("Invalid email");
        }
    }
}
