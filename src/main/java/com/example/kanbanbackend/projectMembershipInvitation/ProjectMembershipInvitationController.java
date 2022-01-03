package com.example.kanbanbackend.projectMembershipInvitation;

import com.example.kanbanbackend.projectMembershipInvitation.models.ProjectMembershipInvitation;
import com.example.kanbanbackend.projectMembershipInvitation.models.ProjectMembershipInvitationAcceptationDTO;
import com.example.kanbanbackend.projectMembershipInvitation.models.ProjectMembershipInvitationInputDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/projectMembershipInvitation")
public class ProjectMembershipInvitationController {
    private ProjectMembershipInvitationService service;

    @GetMapping()
    public Set<ProjectMembershipInvitation> getInvitations(){
        return service.findInvitations();
    }

    @GetMapping("/{id}")
    public Optional<ProjectMembershipInvitation> getInvitation(@PathVariable UUID id){
        return service.findInvitation(id);
    }

    @PostMapping()
    public UUID addInvitation(@Valid @RequestBody ProjectMembershipInvitationInputDTO dto){
        return service.createInvitation(dto);
    }

    @PutMapping("/{id}")
    public void resolveInvitation(@Valid @RequestBody ProjectMembershipInvitationAcceptationDTO acceptationDTO, @PathVariable UUID id){
        service.resolveInvitation(acceptationDTO,id);
    }



    @DeleteMapping("/{id}")
    public void deleteInvitation(@PathVariable UUID id){
        service.removeInvitation(id);
    }
}
