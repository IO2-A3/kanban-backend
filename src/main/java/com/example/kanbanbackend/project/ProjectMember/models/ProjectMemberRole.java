package com.example.kanbanbackend.project.ProjectMember.models;

import com.example.kanbanbackend.user.models.UserListDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjectMemberRole {
    private UserListDto User;
    private ProjectRole role;
}
