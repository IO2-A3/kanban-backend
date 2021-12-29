package com.example.kanbanbackend.user;

import com.example.kanbanbackend.user.models.UserIdDto;
import com.example.kanbanbackend.user.models.UserListDto;
import com.example.kanbanbackend.user.models.UserUpdateWebInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public UserIdDto getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Resource updated succesfully")
    public void updateAnUser(@RequestBody UserUpdateWebInput input,
                            @PathVariable String id) {
        userService.updateAnUser(id, input);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Resource deleted succesfully")
    public void deleteAnUser(@PathVariable("id") String id) {
        userService.deleteAnUser(id);
    }
}
