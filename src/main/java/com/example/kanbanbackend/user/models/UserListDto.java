package com.example.kanbanbackend.user.models;

import com.example.kanbanbackend.project.ProjectMember.models.ProjectMember;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
public class UserListDto {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
}
