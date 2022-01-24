package com.example.kanbanbackend.project.ProjectMember;

import com.example.kanbanbackend.project.ProjectMember.models.ProjectMemberCommand;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectMemberKey;
import com.example.kanbanbackend.projectMembershipInvitation.models.ProjectMembershipInvitationInputDTO;

interface ProjectMemberService {
    ProjectMemberKey addProjectMember(ProjectMembershipInvitationInputDTO inputDTO);
    void updateProjectMemberRole(ProjectMemberCommand command);
}
