package com.example.kanbanbackend.project.ProjectMember.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectMemberIdDto {
    private String projectId;
    private String userId;
    private ProjectRole role;
}
