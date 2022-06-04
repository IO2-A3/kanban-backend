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

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            if (jwtUtil.validateToken(jwt, httpServletRequest)) {
                try {
                    id = jwtUtil.extractId(jwt);
                } catch (Exception e) {
                    throw new ExpiredTokenException("Token is probably expired!");
                }
            }
        }


        if (id != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var user = userRepository.findById(UUID.fromString(id)).orElseThrow(() -> new IncorrectIdInputException("Wrong id"));
            var userDetails = new User(user.getUsername(), user.getPassword(), new ArrayList<>());

            if (jwtUtil.validateToken(jwt, userDetails)) {

                var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
