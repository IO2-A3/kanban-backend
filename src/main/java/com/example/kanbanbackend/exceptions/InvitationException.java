package com.example.kanbanbackend.exceptions;

public class InvitationException extends IllegalArgumentException{
    public InvitationException(String errorMessage) {
        super(errorMessage);
    }
}
