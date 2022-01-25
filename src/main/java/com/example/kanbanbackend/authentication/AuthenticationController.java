package com.example.kanbanbackend.authentication;

import com.example.kanbanbackend.UI.MyUserDetailsService;
import com.example.kanbanbackend.authentication.models.AuthenticateRequest;
import com.example.kanbanbackend.authentication.models.AuthenticationDto;
import com.example.kanbanbackend.authentication.models.ExpiredTokenException;
import com.example.kanbanbackend.exceptions.WrongEmailException;
import com.example.kanbanbackend.exceptions.WrongUsernameException;
import com.example.kanbanbackend.user.UserService;
import com.example.kanbanbackend.user.models.UserServiceCommand;
import com.example.kanbanbackend.user.models.UserWebInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;
    private final JwtUtil jwtTokenUtil;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticateRequest authenticateRequest, HttpServletResponse response) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticateRequest.getUsername(), authenticateRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password");
        }

        final var userDetails = userDetailsService
                .loadUserByUsername(authenticateRequest.getUsername());

        final String accessToken = jwtTokenUtil.generateToken(userDetails, 1000 * 60 * 15);
        final String refreshToken = jwtTokenUtil.generateToken(userDetails, 1000 * 60 * 60 * 24 * 7);

        var cookie = new Cookie("RefreshToken", refreshToken);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(new AuthenticationDto(accessToken));
    }

    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "User created successfully")
    public void registerAnUser(@Valid @RequestBody UserWebInput webInput) {
        userService.addUser(UserServiceCommand.builder().webInput(webInput).build());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({WrongEmailException.class, WrongUsernameException.class})
    public RuntimeException registerExceptions(RuntimeException exception) {
        return exception;
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@CookieValue("RefreshToken") String refreshToken) {
        var username = userService.getUserName(UUID.fromString(jwtTokenUtil.extractId(refreshToken)));

        if (!jwtTokenUtil.isTokenExpired(refreshToken)) {
            var accessToken = jwtTokenUtil.generateToken(userDetailsService.loadUserByUsername(username), 1000 * 60 * 15);

            return ResponseEntity.ok(new AuthenticationDto(accessToken));
        } else {
            throw new ExpiredTokenException("Token has expired!");
        }
    }
}
