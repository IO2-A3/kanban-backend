package com.example.kanbanbackend.projectMembershipInvitation;


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


    public UUID createInvitation(ProjectMembershipInvitationInputDTO dto){
        var invitation = ProjectMembershipInvitation.builder()
                .id(UUID.randomUUID())
                .userId(dto.getUserId())
                .projectId(dto.getProjectId())//todo: sprawdzac czy user i project istnieja ale nie mam modułu usera
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

    public void removeInvitation(UUID id){
        repository.deleteById(id);
    }


}
