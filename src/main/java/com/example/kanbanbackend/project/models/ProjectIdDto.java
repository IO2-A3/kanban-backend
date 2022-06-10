package com.example.kanbanbackend.project.models;

import com.example.kanbanbackend.list.models.ListSetDto;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectMemberRole;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class ProjectIdDto {
    private Timestamp createdAt;
    private String name;
    private UUID id;
    private Set<ListSetDto> listSet;
    private Set<ProjectMemberRole> projectMemberRoles;
}
