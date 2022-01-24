package com.example.kanbanbackend.projectMembershipInvitation.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ProjectMembershipInvitationInputDTO {
    @NotNull
    private UUID userId;
    @NotNull
    private UUID projectId;
}
