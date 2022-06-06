package com.example.kanbanbackend.projectMembershipInvitation.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class AddProjectMemberDTO {
        @NotNull
    private UUID userId;
    @NotNull
    private UUID projectId;
}
