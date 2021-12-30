package com.example.kanbanbackend.project.ProjectMember.models;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class ProjectMemberCommand {
    private final UUID userId;
    private final UUID projectId;
    private final ProjectRole role;
}
