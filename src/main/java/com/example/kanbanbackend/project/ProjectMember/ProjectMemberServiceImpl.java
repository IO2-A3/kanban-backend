package com.example.kanbanbackend.project.ProjectMember;

import com.example.kanbanbackend.exceptions.IncorrectIdInputException;
import com.example.kanbanbackend.exceptions.InvitationException;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectMember;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectMemberCommand;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectMemberKey;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectRole;
import com.example.kanbanbackend.project.ProjectRepository;
import com.example.kanbanbackend.projectMembershipInvitation.ProjectMembershipInvitationRepository;
import com.example.kanbanbackend.projectMembershipInvitation.models.ProjectMembershipInvitationInputDTO;
import com.example.kanbanbackend.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class ProjectMemberServiceImpl implements ProjectMemberService{
    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMembershipInvitationRepository invitationRepository;

    @Override
    public ProjectMemberKey addProjectMember(ProjectMembershipInvitationInputDTO inputDTO) {
        var key = getProjectMemberKey(inputDTO.getProjectId(),inputDTO.getUserId());
        var invitation = invitationRepository.findByUserIdAndProjectId(inputDTO.getUserId(),inputDTO.getProjectId()).get();
        if(!invitation.getPending()){
            if(invitation.getIsAccepted()){
                projectMemberRepository.save(
                        ProjectMember.builder()
                                .role(ProjectRole.COLLABORATOR)
                                .id(key).build()
                );

                return key;
            } else throw new InvitationException("Invitation is declined");
        }
        else throw new InvitationException("Invitation is still pending");
    }

    @Override
    public void updateProjectMemberRole(ProjectMemberCommand command) {
        var projectMember = projectMemberRepository.findById(getProjectMemberKey(command.getProjectId(), command.getUserId())).orElseThrow(() -> new IncorrectIdInputException("Wrong project or user!"));

        projectMember.setRole(command.getRole());

        projectMemberRepository.save(projectMember);
    }

    private ProjectMemberKey getProjectMemberKey(UUID projectId, UUID userId) {
        var project = projectRepository.findById(projectId).orElseThrow(() -> new IncorrectIdInputException("Wrong project Id"));
        var user = userRepository.findById(userId).orElseThrow(() -> new IncorrectIdInputException("Wrong user Id"));

        return new ProjectMemberKey(user, project);
    }
}
