package com.example.kanbanbackend.project.models;

import com.example.kanbanbackend.project.ProjectMember.models.ProjectMemberRole;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
public class ProjectIdDto {
    private Timestamp createdAt;
    private String name;
    private Set<ProjectMemberRole> projectMemberRoles;
}
