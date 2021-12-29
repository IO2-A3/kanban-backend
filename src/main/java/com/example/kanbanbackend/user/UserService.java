package com.example.kanbanbackend.user;

import com.example.kanbanbackend.user.models.UserIdDto;
import com.example.kanbanbackend.user.models.UserListDto;
import com.example.kanbanbackend.user.models.UserServiceCommand;
import com.example.kanbanbackend.user.models.UserUpdateWebInput;

import java.util.List;

public interface UserService {
    void addUser(UserServiceCommand command);

    List<UserListDto> getUsers();

    void deleteAnUser(String id);

    String getUserName(String id);

    UserIdDto getUserById(String id);

    void updateAnUser(String id, UserUpdateWebInput input);
}
