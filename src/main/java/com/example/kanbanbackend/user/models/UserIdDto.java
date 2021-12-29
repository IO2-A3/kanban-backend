package com.example.kanbanbackend.user.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class UserIdDto {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private Timestamp createdAt;
}
