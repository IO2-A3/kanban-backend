package com.example.kanbanbackend.user.models;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class UserWebInput {
    @NotBlank
    @Size(min = 5)
    private String username;
    @NotNull
    @Size(min = 5)
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
}
