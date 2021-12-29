package com.example.kanbanbackend.authentication.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class AuthenticateRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
