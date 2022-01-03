package com.example.kanbanbackend.projectMembershipInvitation.models;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class ProjectMembershipInvitationAcceptationDTO {
    @NotNull
    private boolean resolve;
}
