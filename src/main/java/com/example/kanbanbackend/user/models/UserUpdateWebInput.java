package com.example.kanbanbackend.user.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateWebInput {
    private String email;
    private String username;
}
