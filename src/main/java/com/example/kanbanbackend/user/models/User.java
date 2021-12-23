package com.example.kanbanbackend.user.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "UserData")
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private Timestamp createdAt;
}
