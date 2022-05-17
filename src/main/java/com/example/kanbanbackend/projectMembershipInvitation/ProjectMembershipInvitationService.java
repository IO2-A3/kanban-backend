package com.example.kanbanbackend.projectMembershipInvitation;


import com.example.kanbanbackend.exceptions.IncorrectIdInputException;
import com.example.kanbanbackend.exceptions.InvitationException;
import com.example.kanbanbackend.project.ProjectMember.ProjectMemberRepository;
import com.example.kanbanbackend.project.ProjectMember.ProjectMemberServiceImpl;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectRole;
import com.example.kanbanbackend.projectMembershipInvitation.models.ProjectMembershipInvitation;
import com.example.kanbanbackend.projectMembershipInvitation.models.ProjectMembershipInvitationAcceptationDTO;
import com.example.kanbanbackend.projectMembershipInvitation.models.ProjectMembershipInvitationInputDTO;
import com.example.kanbanbackend.security.AuthenticationFacade;
import com.example.kanbanbackend.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
@AllArgsConstructor
public class ProjectMembershipInvitationService {
    private ProjectMembershipInvitationRepository repository;
    private UserRepository userRepository;
    private AuthenticationFacade authenticationFacade;
    private ProjectMemberServiceImpl projectMemberService;


    public UUID createInvitation(ProjectMembershipInvitationInputDTO dto){
        isUserOwner(dto.getUserId(),dto.getProjectId());
        if(isUserAlreadyInvited(dto.getProjectId(),dto.getUserId())){
            throw new InvitationException("This user already received invitation");
        }
        userRepository.findById(dto.getUserId()).orElseThrow(() -> new IncorrectIdInputException("User with this id don't exist"));
        var invitation = ProjectMembershipInvitation.builder()
                .id(UUID.randomUUID())
                .userId(dto.getUserId())
                .projectId(dto.getProjectId())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .pending(true)
                .isAccepted(false)
                .build();
        repository.save(invitation);
        return invitation.getId();
    }

    public Set<ProjectMembershipInvitation> findInvitations(){
        return new HashSet<>(repository.findAll());
    }



    public List<ProjectMembershipInvitation> findInvitationsByUserId(UUID userId){
        return repository.findByUserId(userId);
    }
    public void resolveInvitation(ProjectMembershipInvitationAcceptationDTO acceptationDTO, UUID invitationID){
        var userId = authenticationFacade.getCurrentAuthenticatedUser();
        isInvitationDestinationCorrect(invitationID,userId);

        var invitation = repository.findById(invitationID).get();
        if(!invitation.getPending()){
            var decision = acceptationDTO.isResolve();

            invitation.setPending(false);
            invitation.setIsAccepted(decision);

            repository.save(invitation);

            if(acceptationDTO.isResolve()){
                projectMemberService.addProjectMember(new ProjectMembershipInvitationInputDTO(invitation.getProjectId(),invitation.getUserId()));
            }

        }

    }


    private boolean isUserAlreadyInvited(UUID projectId, UUID userId){
        return repository.existsByUserIdAndProjectId(userId,projectId);
    }

    private void isUserOwner(UUID userId, UUID projectId){
        var projectMemberKey = projectMemberService.getProjectMemberKey(projectId,userId);
        var projectMember = projectMemberService.findProjectMemberById(projectMemberKey);
        if(!projectMember.getRole().equals(ProjectRole.OWNER)) throw new InvitationException("User is not owner of project");
    }

    private void isInvitationDestinationCorrect(UUID authenthicationUserId, UUID userId){
        if(!authenthicationUserId.equals(userId)) throw new InvitationException("Incorrect invitation");
    }
}
