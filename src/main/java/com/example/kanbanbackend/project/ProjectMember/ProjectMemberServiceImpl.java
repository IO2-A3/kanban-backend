package com.example.kanbanbackend.project.ProjectMember;

import com.example.kanbanbackend.exceptions.IncorrectIdInputException;
import com.example.kanbanbackend.exceptions.InvitationException;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectMember;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectMemberCommand;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectMemberKey;
import com.example.kanbanbackend.project.ProjectRepository;
import com.example.kanbanbackend.projectMembershipInvitation.ProjectMembershipInvitationRepository;
import com.example.kanbanbackend.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProjectMemberServiceImpl implements ProjectMemberService{
    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMembershipInvitationRepository invitationRepository;

    @Override
    public ProjectMemberKey addProjectMember(ProjectMemberCommand command) {
        var key = getProjectMemberKey(command);
        var invitation = invitationRepository.findByUserIdAndProjectId(command.getUserId(),command.getProjectId()).get();
        if(!invitation.getPending()){
            if(invitation.getIsAccepted()){
                projectMemberRepository.save(
                        ProjectMember.builder()
                                .role(command.getRole())
                                .id(key).build()
                );

                return key;
            } else throw new InvitationException("Invitation is declined");
        }
        else throw new InvitationException("Invitation is still pending");
    }

    @Override
    public void updateProjectMemberRole(ProjectMemberCommand command) {
        var projectMember = projectMemberRepository.findById(getProjectMemberKey(command)).orElseThrow(() -> new IncorrectIdInputException("Wrong project or user!"));

        projectMember.setRole(command.getRole());

        projectMemberRepository.save(projectMember);
    }

    private ProjectMemberKey getProjectMemberKey(ProjectMemberCommand command) {
        var project = projectRepository.findById(command.getProjectId()).orElseThrow(() -> new IncorrectIdInputException("Wrong project Id"));
        var user = userRepository.findById(command.getUserId()).orElseThrow(() -> new IncorrectIdInputException("Wrong user Id"));

        return new ProjectMemberKey(user, project);
    }
}
