package com.example.kanbanbackend.project;

import com.example.kanbanbackend.exceptions.IncorrectIdInputException;
import com.example.kanbanbackend.list.models.ListSetDto;
import com.example.kanbanbackend.project.ProjectMember.ProjectMemberServiceImpl;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectMemberRole;
import com.example.kanbanbackend.project.models.Project;
import com.example.kanbanbackend.project.models.ProjectIdDto;
import com.example.kanbanbackend.project.models.ProjectInputDTO;
import com.example.kanbanbackend.project.models.ProjectSetDto;
import com.example.kanbanbackend.security.AuthenticationFacade;
import com.example.kanbanbackend.user.models.UserListDto;
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
    private final ProjectMemberServiceImpl projectMemberService;
    private final AuthenticationFacade authenticationFacade;
    private final ModelMapper mapper;

    public UUID createProject(ProjectInputDTO projectInputDTO){
        var projectId = UUID.randomUUID();
        var userId = authenticationFacade.getCurrentAuthenticatedUser().getId();

        var project = Project.builder()
                .id(projectId)
                .name(projectInputDTO.getName())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();

        projectRepository.save(project);

        projectMemberService.addProjectMemberOwner(projectId, userId);
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
