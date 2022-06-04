package com.example.kanbanbackend.security;

import com.example.kanbanbackend.user.models.UserPublicDTO;

import java.util.List;

public interface AuthenticationFacade {
    UserPublicDTO getCurrentAuthenticatedUser();
    List<String> getCurrentAuthenticatedUserAuthorities();
    Boolean checkIfCurrentAuthenticatedUserAnOwnerOfThisProject();
}
