package com.example.kanbanbackend.projectMembershipInvitation;

import com.example.kanbanbackend.projectMembershipInvitation.models.ProjectMembershipInvitation;
import com.example.kanbanbackend.projectMembershipInvitation.models.ProjectMembershipInvitationAcceptationDTO;
import com.example.kanbanbackend.projectMembershipInvitation.models.ProjectMembershipInvitationInputDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/project-membership-invitation")
public class ProjectMembershipInvitationController {
    private ProjectMembershipInvitationService service;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public Set<ProjectMembershipInvitation> getInvitations(){
        return service.findInvitations();
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("isAuthenticated()")
    public List<ProjectMembershipInvitation> getInvitationsByUserId(@PathVariable UUID userId){
        return service.findInvitationsByUserId(userId);
    }
    @PostMapping()
//    @PreAuthorize("isAuthenticated()")
    public UUID addInvitation(@Valid @RequestBody ProjectMembershipInvitationInputDTO dto){
        return service.createInvitation(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public void resolveInvitation(@Valid @RequestBody ProjectMembershipInvitationAcceptationDTO acceptationDTO, @PathVariable UUID id){
        service.resolveInvitation(acceptationDTO,id);
    }




}
