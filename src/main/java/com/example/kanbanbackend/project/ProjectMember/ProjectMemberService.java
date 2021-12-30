package com.example.kanbanbackend.project.ProjectMember;

import com.example.kanbanbackend.project.ProjectMember.models.ProjectMemberCommand;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectMemberKey;

interface ProjectMemberService {
    ProjectMemberKey addProjectMember(ProjectMemberCommand command);
    void updateProjectMemberRole(ProjectMemberCommand command);
}
