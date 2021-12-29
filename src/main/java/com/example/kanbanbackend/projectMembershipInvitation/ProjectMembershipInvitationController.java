package com.example.kanbanbackend.projectMembershipInvitation;

import com.example.kanbanbackend.projectMembershipInvitation.models.ProjectMembershipInvitation;
import com.example.kanbanbackend.projectMembershipInvitation.models.ProjectMembershipInvitationInputDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ProjectMembershipInvitationController {
    private ProjectMembershipInvitationService service;

    @GetMapping("/projectMembershipInvitations")
    public Set<ProjectMembershipInvitation> getInvitations(){
        return service.findInvitations();
    }

    @GetMapping("/projectMembershipInvitation/{id}")
    public Optional<ProjectMembershipInvitation> getInvitation(@PathVariable UUID id){
        return service.findInvitation(id);
    }

    @PostMapping("/projectMembershipInvitation")
    public UUID addInvitation(@RequestBody ProjectMembershipInvitationInputDTO dto){
        return service.createInvitation(dto);
    }

    @DeleteMapping("/projectMembershipInvitation/{id}")
    public void deleteInvitation(@PathVariable UUID id){
        service.removeInvitation(id);
    }
}
