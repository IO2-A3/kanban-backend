package com.example.kanbanbackend.projectMembershipInvitation;


import com.example.kanbanbackend.exceptions.IncorrectIdInputException;
import com.example.kanbanbackend.projectMembershipInvitation.models.ProjectMembershipInvitation;
import com.example.kanbanbackend.projectMembershipInvitation.models.ProjectMembershipInvitationAcceptationDTO;
import com.example.kanbanbackend.projectMembershipInvitation.models.ProjectMembershipInvitationInputDTO;
import com.example.kanbanbackend.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProjectMembershipInvitationService {
    private ProjectMembershipInvitationRepository repository;
    private UserRepository userRepository;


    public UUID createInvitation(ProjectMembershipInvitationInputDTO dto){
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

    public Optional<ProjectMembershipInvitation> findInvitation(UUID id){
        return repository.findById(id);
    }

    public void resolveInvitation(ProjectMembershipInvitationAcceptationDTO acceptationDTO, UUID invitationID){
        var decision = acceptationDTO.isResolve();
        var invitation = repository.findById(invitationID).get();

        invitation.setPending(false);
        invitation.setIsAccepted(decision);
        repository.save(invitation);

    }

    public void removeInvitation(UUID id){
        repository.deleteById(id);
    }


}
