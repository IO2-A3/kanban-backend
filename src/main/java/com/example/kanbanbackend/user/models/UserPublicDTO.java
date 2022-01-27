package com.example.kanbanbackend.user.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

/*
* Minimal User fields that are safe to expose to the client.
* */
@Getter
@Setter
public class UserPublicDTO {
    private UUID id;
    private String email;
    private String username;
    private Timestamp createdAt;
}
