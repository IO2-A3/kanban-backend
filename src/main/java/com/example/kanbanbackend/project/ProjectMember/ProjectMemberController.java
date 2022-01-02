package com.example.kanbanbackend.project.ProjectMember;

import com.example.kanbanbackend.project.ProjectMember.models.ProjectMemberCommand;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectMemberWebInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/project/{projectId}/user/{userId}")
public class ProjectMemberController {
    private final ProjectMemberService service;

    @PutMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "User role changed succesfully")
    public void updateProjectMemberRole(@PathVariable("projectId") UUID projectId,
                                 @PathVariable("userId") UUID userId,
                                 @RequestBody ProjectMemberWebInput webInput) {
        service.updateProjectMemberRole(ProjectMemberCommand.builder()
                .projectId(projectId)
                .userId(userId)
                .role(webInput.getRole()).build());
    }
}