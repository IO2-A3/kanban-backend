package com.example.kanbanbackend.authentication;

import com.example.kanbanbackend.authentication.models.ExpiredTokenException;
import com.example.kanbanbackend.exceptions.IncorrectIdInputException;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectRole;
import com.example.kanbanbackend.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = httpServletRequest.getHeader("Authorization");

        String id = null;
        String jwt = null;

        if (authorizationHeader != null) {
            try {
                jwt = jwtUtil.extractBearerFromHeader(authorizationHeader);
                id = jwtUtil.extractId(jwt);
            } catch (Exception e) {
                // @TODO: change exception
                throw new ExpiredTokenException("Token is probably expired!");
            }
        }

        // Check if token is not malformed and if stores user ID
        if (authorizationHeader != null && jwtUtil.validateToken(jwt) && id != null) {
            var user = userRepository.findById(UUID.fromString(id)).orElseThrow(() -> new IncorrectIdInputException("Wrong id"));

            var userProjectsIdsWhoseIsOwner = user.getProjectMembers().stream()
                    .filter(projectMember -> projectMember.getRole() == ProjectRole.OWNER)
                    .map(projectMember -> new SimpleGrantedAuthority(projectMember.getId().getProject().getId().toString()))
                    .collect(Collectors.toList());

            var userDetails = new User(user.getUsername(), user.getPassword(), userProjectsIdsWhoseIsOwner);

            // Insert authenticated user data into Security Context
            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );

            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
