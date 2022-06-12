package com.example.kanbanbackend.project;

import com.example.kanbanbackend.authentication.JwtUtil;
import com.example.kanbanbackend.list.ListService;
import com.example.kanbanbackend.list.models.List;
import com.example.kanbanbackend.list.models.ListInputDto;
import com.example.kanbanbackend.project.models.*;
import com.example.kanbanbackend.security.OwnerPermission;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Set;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/project")
public class ProjectController {
    private final ProjectService projectService;
    private final ListService listService;
    private final JwtUtil jwtUtil;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public Set<ProjectSetDto> getProjects() {
        return projectService.findProjectsByUser();
    }

    @GetMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ProjectIdDto getProject(@PathVariable UUID id){
        return projectService.findProject(id);
    }

    @PostMapping
    public Project addProject(@Valid @RequestBody ProjectInputDTO projectInputDTO){
        return projectService.createProject(projectInputDTO);
    }

    @PostMapping("/list")
    @PreAuthorize("isAuthenticated()")
    @OwnerPermission(value = "#inputDto.projectId")
    public List addList(@Valid @RequestBody ListInputDto inputDto) {
        return listService.createList(inputDto);
    }

}
