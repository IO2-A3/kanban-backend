package com.example.kanbanbackend.user;

import com.example.kanbanbackend.user.models.UserListDto;
import com.example.kanbanbackend.user.models.UserServiceCommand;

import java.util.List;

public interface UserService {
    void addUser(UserServiceCommand command);

    List<UserListDto> getUsers();

    void deleteAnUser(String id);

    String getUserName(String id);
}
