package com.example.kanbanbackend.authentication.models;

import com.example.kanbanbackend.user.models.UserPublicDTO;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class AuthenticationDto extends PublicTokenDto{
    private UserPublicDTO user;
}
