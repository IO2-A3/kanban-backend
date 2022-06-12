package com.example.kanbanbackend.project;

import com.example.kanbanbackend.exceptions.IncorrectIdInputException;
import com.example.kanbanbackend.list.models.ListSetDto;
import com.example.kanbanbackend.project.ProjectMember.ProjectMemberServiceImpl;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectMemberRole;
import com.example.kanbanbackend.project.models.*;
import com.example.kanbanbackend.security.AuthenticationFacade;
import com.example.kanbanbackend.user.models.UserListDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMemberServiceImpl projectMemberService;
    private final AuthenticationFacade authenticationFacade;
    private final ModelMapper mapper;

    public Project createProject(ProjectInputDTO inputDTO){
        var projectId = UUID.randomUUID();
        var userId = authenticationFacade.getCurrentAuthenticatedUser().getId();

        var project = Project.builder()
                .id(projectId)
                .name(inputDTO.getName())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();

        projectRepository.save(project);


        projectMemberService.addProjectMemberOwner(projectId, userId);
        return project;
    }

    public Set<ProjectSetDto> findProjectsByUser(){
        var projects = projectRepository.findAll();

        var requestingUserId = authenticationFacade.getCurrentAuthenticatedUser().getId();

        return projects.stream()
                .filter(project -> projectMemberService.checkIfExistsById(projectMemberService.getProjectMemberKey(project.getId(), requestingUserId)))
                .map(project -> mapper.map(project, ProjectSetDto.class))
                .sorted(Comparator.comparingLong(p -> p.getName().hashCode()))
                .collect(Collectors.toCollection(LinkedHashSet::new));

    }

    public ProjectIdDto findProject(UUID projectId){
        var project = projectRepository.findById(projectId).orElseThrow(() -> new IncorrectIdInputException("Wrong id!"));

        var projectMemberRoles = project.getProjectMembers().stream()
                .map(projectMember -> new ProjectMemberRole(
                        mapper.map(projectMember.getId().getUser(), UserListDto.class), projectMember.getRole()))
                .collect(Collectors.toSet());

        var listsSetDto = project.getListSet().stream()
                .map(list -> mapper.map(list, ListSetDto.class))
                .sorted(Comparator.comparingInt(ListSetDto::getListOrder))
                .collect(Collectors.toCollection(LinkedHashSet::new));

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
