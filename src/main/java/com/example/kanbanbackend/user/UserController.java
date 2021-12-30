package com.example.kanbanbackend.user;

import com.example.kanbanbackend.user.models.UserIdDto;
import com.example.kanbanbackend.user.models.UserListDto;
import com.example.kanbanbackend.user.models.UserUpdateWebInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserListDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserIdDto getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Resource updated succesfully")
    public void updateAnUser(@RequestBody UserUpdateWebInput input,
                            @PathVariable UUID id) {
        userService.updateAnUser(id, input);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Resource deleted succesfully")
    public void deleteAnUser(@PathVariable("id") UUID id) {
        userService.deleteAnUser(id);
    }
}
