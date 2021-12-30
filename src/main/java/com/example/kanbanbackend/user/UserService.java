package com.example.kanbanbackend.user;

import com.example.kanbanbackend.user.models.UserIdDto;
import com.example.kanbanbackend.user.models.UserListDto;
import com.example.kanbanbackend.user.models.UserServiceCommand;
import com.example.kanbanbackend.user.models.UserUpdateWebInput;

import java.util.List;
import java.util.UUID;

public interface UserService {
    void addUser(UserServiceCommand command);

    List<UserListDto> getUsers();

    void deleteAnUser(UUID id);

    String getUserName(UUID id);

    UserIdDto getUserById(UUID id);

    void updateAnUser(UUID id, UserUpdateWebInput input);
}
