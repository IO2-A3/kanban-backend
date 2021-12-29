package com.example.kanbanbackend.project;

import com.example.kanbanbackend.project.models.ProjectIdDto;
import com.example.kanbanbackend.project.models.ProjectInputDTO;
import com.example.kanbanbackend.project.models.ProjectSetDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/project")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public Set<ProjectSetDto> getProjects(){
        return projectService.findProjects();
    }

    @GetMapping("{id}")
    public ProjectIdDto getProject(@PathVariable String id){
        return projectService.findProject(id);
    }

    @PostMapping
    public void addProject(@Valid @RequestBody ProjectInputDTO projectInputDTO){
        projectService.createProject(projectInputDTO);
    }

    @DeleteMapping("{id}")
    public void deleteProject(@PathVariable String id){
        projectService.removeProject(id);
    }
}
