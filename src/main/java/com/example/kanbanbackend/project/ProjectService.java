package com.example.kanbanbackend.project;

import com.example.kanbanbackend.exceptions.IncorrectIdInputException;
import com.example.kanbanbackend.project.models.Project;
import com.example.kanbanbackend.project.models.ProjectIdDto;
import com.example.kanbanbackend.project.models.ProjectInputDTO;
import com.example.kanbanbackend.project.models.ProjectSetDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ModelMapper mapper;

    public UUID createProject(ProjectInputDTO projectInputDTO){
        var project = Project.builder()
                .id(UUID.randomUUID())
                .name(projectInputDTO.getName())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();

        projectRepository.save(project);
        return project.getId();
    }

    public Set<ProjectSetDto> findProjects(){
        var projects = projectRepository.findAll();
        return projects.stream()
                .map(project -> mapper.map(project, ProjectSetDto.class))
                .collect(Collectors.toSet());
    }

    public ProjectIdDto findProject(UUID projectId){
        var project = projectRepository.findById(projectId).orElseThrow(() -> new IncorrectIdInputException("Wrong id!"));
        return mapper.map(project, ProjectIdDto.class);
    }

    public void removeProject(UUID projectID){
        projectRepository.findById(projectID);
    }
}
