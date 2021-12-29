package com.example.kanbanbackend.project.ProjectMember;

import com.example.kanbanbackend.project.ProjectMember.models.ProjectMemberCommand;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectMemberIdDto;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectMemberSetDto;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectRole;
import com.example.kanbanbackend.user.models.UserListDto;

import java.util.Set;

interface ProjectMemberService {
    ProjectRole getUserRole(ProjectMemberCommand command);
    Set<UserListDto> getProjectMembersByProjectId(ProjectMemberCommand command);
    String addProjectMember(ProjectMemberCommand command);
    Set<ProjectMemberSetDto> getProjectMembers(ProjectMemberCommand command);
    ProjectMemberIdDto getProjectMemberById(String id);
    void deleteProjectMember(String id);
    void updateUserRole(ProjectMemberCommand command);
}
