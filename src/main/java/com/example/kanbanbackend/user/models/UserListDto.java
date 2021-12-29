package com.example.kanbanbackend.user.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class UserListDto {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private Timestamp createdAt;
}
