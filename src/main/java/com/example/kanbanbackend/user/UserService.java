package com.example.kanbanbackend.user;

import com.example.kanbanbackend.user.models.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UserService extends UserDetailsService {
    void addUser(UserServiceCommand command);

    List<UserListDto> getUsers();

    void deleteAnUser(UUID id);

    String getUserName(UUID id);

    UserIdDto getUserById(UUID id);

    UserPublicDTO getUserByUsername(String username);

    void updateAnUser(UUID id, UserUpdateWebInput input);
}
