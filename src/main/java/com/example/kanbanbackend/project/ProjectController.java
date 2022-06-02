package com.example.kanbanbackend.project;

import com.example.kanbanbackend.authentication.JwtUtil;
import com.example.kanbanbackend.list.ListService;
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
    public Set<ProjectSetDto> getProjects(HttpServletRequest request) throws Exception {
        var userId = jwtUtil.getIdFromRequest(request);
        return projectService.findProjectsByUser(userId);
    }

    @GetMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ProjectIdDto getProject(@PathVariable UUID id){
        return projectService.findProject(id);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public Project addProject(@Valid @RequestBody ProjectInputDTO projectInputDTO, HttpServletRequest request) throws Exception {
        var userId = jwtUtil.getIdFromRequest(request);
        ProjectCreateInputDTO createProjectParams = new ProjectCreateInputDTO();
        createProjectParams.setUserId(userId);
        createProjectParams.setName(projectInputDTO.getName());
        return projectService.createProject(createProjectParams);
    }

    @PostMapping("/list")
    @PreAuthorize("isAuthenticated()")
    @OwnerPermission(value = "#inputDto.projectId")
    public UUID addList(@Valid @RequestBody ListInputDto inputDto, HttpServletRequest request) throws Exception {
        var userId = jwtUtil.getIdFromRequest(request);
        return listService.createList(inputDto);
    }

}
