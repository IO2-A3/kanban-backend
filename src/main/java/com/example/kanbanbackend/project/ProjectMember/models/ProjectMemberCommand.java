package com.example.kanbanbackend.project.ProjectMember.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProjectMemberCommand {
    private final String userId;
    private final String projectId;
    private final ProjectRole role;
}
