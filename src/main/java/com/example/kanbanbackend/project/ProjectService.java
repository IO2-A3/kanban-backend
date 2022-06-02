package com.example.kanbanbackend.project;

import com.example.kanbanbackend.exceptions.IncorrectIdInputException;
import com.example.kanbanbackend.list.models.ListSetDto;
import com.example.kanbanbackend.project.ProjectMember.ProjectMemberServiceImpl;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectMemberRole;
import com.example.kanbanbackend.project.models.*;
import com.example.kanbanbackend.user.models.UserListDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMemberServiceImpl projectMemberService;
    private final ModelMapper mapper;

    public Project createProject(ProjectCreateInputDTO createProjectParams){
        var projectId = UUID.randomUUID();

        var project = Project.builder()
                .id(projectId)
                .name(createProjectParams.getName())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();

        projectRepository.save(project);

        projectMemberService.addProjectMemberOwner(projectId, createProjectParams.getUserId());
        return project;
    }

    public Set<ProjectSetDto> findProjectsByUser(UUID userId){
        // @TODO: fetch only those where user is participant
        var projects = projectRepository.findAll();
        return projects.stream()
                .map(project -> mapper.map(project, ProjectSetDto.class))
                .collect(Collectors.toSet());
    }

    public ProjectIdDto findProject(UUID projectId){
        var project = projectRepository.findById(projectId).orElseThrow(() -> new IncorrectIdInputException("Wrong id!"));

        var projectMemberRoles = project.getProjectMembers().stream()
                .map(projectMember -> new ProjectMemberRole(
                        mapper.map(projectMember.getId().getUser(), UserListDto.class), projectMember.getRole()))
                .collect(Collectors.toSet());

        var listsSetDto = project.getListSet().stream()
                .map(list -> mapper.map(list, ListSetDto.class))
                .collect(Collectors.toSet());


        var result = mapper.map(project, ProjectIdDto.class);
        result.setProjectMemberRoles(projectMemberRoles);
        result.setListSet(listsSetDto);



        return result;
    }

    public void removeProject(UUID projectID){
        var project = projectRepository.findById(projectID).get();
        projectRepository.delete(project);
    }
}
