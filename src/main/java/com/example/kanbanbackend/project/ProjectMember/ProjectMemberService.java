package com.example.kanbanbackend.project.ProjectMember;

import com.example.kanbanbackend.project.ProjectMember.models.ProjectMemberCommand;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectMemberKey;
import com.example.kanbanbackend.projectMembershipInvitation.models.AddProjectMemberDTO;
import com.example.kanbanbackend.projectMembershipInvitation.models.ProjectMembershipInvitationInputDTO;

import java.util.UUID;

interface ProjectMemberService {
    ProjectMemberKey addProjectMember(AddProjectMemberDTO inputDTO);

    void addProjectMemberOwner(UUID projectId, UUID userId);

    void updateProjectMemberRole(ProjectMemberCommand command);
}
