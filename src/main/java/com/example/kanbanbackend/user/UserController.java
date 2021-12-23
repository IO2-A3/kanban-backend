package com.example.kanbanbackend.user;

import com.example.kanbanbackend.user.models.UserListDto;
import com.example.kanbanbackend.user.models.UserServiceCommand;
import com.example.kanbanbackend.user.models.UserWebInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
