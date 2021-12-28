package com.example.kanbanbackend.authentication.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationDto {
    private final String accessToken;
    private final String tokenType = "Bearer";
}
