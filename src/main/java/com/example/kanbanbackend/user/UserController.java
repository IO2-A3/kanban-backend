package com.example.kanbanbackend.user;

import com.example.kanbanbackend.security.JwtUtil;
import com.example.kanbanbackend.security.MyUserDetailsService;
import com.example.kanbanbackend.user.models.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, reason = "User created successfully")
    public String registerAnUser(@Valid @RequestBody UserWebInput webInput) {
        userService.addUser(UserServiceCommand.builder().webInput(webInput).build());

        return "Elo";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserListDto> getUsers() {
        return userService.getUsers();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAnUser(@PathVariable("id") String id) {
        userService.deleteAnUser(id);
    }
}
