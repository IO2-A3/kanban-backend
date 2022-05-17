package com.example.kanbanbackend.security;


import java.util.List;
import java.util.UUID;

public interface AuthenticationFacade {
    UUID getCurrentAuthenticatedUser();
    List<String> getCurrentAuthenticatedUserAuthorities();
    Boolean isCurrentAuthenticatedUserAnAdmin();
}
