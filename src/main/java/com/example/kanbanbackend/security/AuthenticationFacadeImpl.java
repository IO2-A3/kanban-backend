package com.example.kanbanbackend.security;

import com.example.kanbanbackend.exceptions.ForbiddenException;
import com.example.kanbanbackend.user.UserService;
import com.example.kanbanbackend.user.models.UserPublicDTO;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthenticationFacadeImpl implements AuthenticationFacade {
    private final UserService userService;

    @Override
    public UserPublicDTO getCurrentAuthenticatedUser() {

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication instanceof AnonymousAuthenticationToken)) {
            throw new ForbiddenException();
        }

        return userService.getUserByUsername(authentication.getName());

    }

    @Override
    public List<String> getCurrentAuthenticatedUserAuthorities() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication instanceof AnonymousAuthenticationToken)) {
            throw new ForbiddenException();
        }

        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }

    @Override
    public Boolean checkIfCurrentAuthenticatedUserAnOwnerOfThisProject() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication instanceof AnonymousAuthenticationToken)) {
            throw new ForbiddenException();
        }

        return authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .anyMatch(a -> a.equals("A00"));
    }


}
