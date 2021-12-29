package com.example.kanbanbackend.projectMembershipInvitation.models;

import lombok.Getter;
import org.hibernate.annotations.Type;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class ProjectMembershipInvitationInputDTO {
    @NotNull
    private UUID userId;
    @NotNull
    private UUID projectId;
}
