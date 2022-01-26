package com.example.kanbanbackend.project;

import com.example.kanbanbackend.authentication.JwtUtil;
import com.example.kanbanbackend.project.models.ProjectIdDto;
import com.example.kanbanbackend.project.models.ProjectInputDTO;
import com.example.kanbanbackend.project.models.ProjectSetDto;
import com.example.kanbanbackend.project.models.ProjectWebInputDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final JwtUtil jwtUtil;

    @GetMapping
    public Set<ProjectSetDto> getProjects(){
        return projectService.findProjects();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority(#id)")
    public ProjectIdDto getProject(@PathVariable UUID id){
        return projectService.findProject(id);
    }

    @PostMapping
    public UUID addProject(@Valid @RequestBody ProjectWebInputDTO webInputDTO, HttpServletRequest request) throws Exception {
        var userId = jwtUtil.getIdFromRequest(request);

        return projectService.createProject(ProjectInputDTO.builder().name(webInputDTO.getName()).userId(userId).build());
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority(#id)")
    public void deleteProject(@PathVariable UUID id){
        projectService.removeProject(id);
    }
}
