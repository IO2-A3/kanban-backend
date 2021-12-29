package com.example.kanbanbackend.projectMembershipInvitation;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class ProjectMembershipInvitationInputDTO {
    @NotNull
    private UUID userId;
    @NotNull
    private UUID projectId;
}
