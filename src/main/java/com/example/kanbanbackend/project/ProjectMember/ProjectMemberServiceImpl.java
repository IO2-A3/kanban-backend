package com.example.kanbanbackend.project.ProjectMember;

import com.example.kanbanbackend.UI.idGenerator.IdGenerator;
import com.example.kanbanbackend.UI.idGenerator.UuidGenerator;
import com.example.kanbanbackend.exceptions.IncorrectIdInputException;
import com.example.kanbanbackend.project.ProjectMember.models.*;
import com.example.kanbanbackend.project.ProjectRepository;
import com.example.kanbanbackend.user.UserRepository;
import com.example.kanbanbackend.user.models.UserListDto;
import com.sun.xml.bind.v2.schemagen.episode.SchemaBindings;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProjectMemberServiceImpl implements ProjectMemberService{
    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public ProjectRole getUserRole(ProjectMemberCommand command) {
        return ProjectRole.COLLABORATOR;
    }

    @Override
    public Set<UserListDto> getProjectMembersByProjectId(ProjectMemberCommand command) {
        var projectMembers = projectMemberRepository.findAllByProjectId(command.getProjectId());

        return projectMembers.stream()
                .map(projectMember ->
                        mapper.map(userRepository.findById(projectMember.getUserId()), UserListDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public String addProjectMember(ProjectMemberCommand command) {
        projectRepository.findById(command.getProjectId()).orElseThrow(() -> new IncorrectIdInputException("Wrong project Id"));
        userRepository.findById(command.getUserId()).orElseThrow(() -> new IncorrectIdInputException("Wrong user Id"));

        var idGenerator = new UuidGenerator();
        var id = idGenerator.generateId();

        projectMemberRepository.save(
                ProjectMember.builder()
                        .projectId(command.getProjectId())
                        .userId(command.getUserId())
                        .role(command.getRole())
                        .id(id).build()
        );

        return id;
    }

    @Override
    public Set<ProjectMemberSetDto> getProjectMembers(ProjectMemberCommand command) {
        var projectMembers = projectMemberRepository.findAll();
        return projectMembers.stream()
                .map(projectMember -> mapper.map(projectMember, ProjectMemberSetDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public ProjectMemberIdDto getProjectMemberById(String id) {
        var projectMember = projectMemberRepository.findById(id).orElseThrow(() -> new IncorrectIdInputException("Wrong projectMember id"));
        return mapper.map(projectMember, ProjectMemberIdDto.class);
    }

    @Override
    public void deleteProjectMember(String id) {
        projectMemberRepository.deleteById(id);
    }

    @Override
    public void updateUserRole(ProjectMemberCommand command) {
        var projectMember = projectMemberRepository.findByUserIdAndProjectId(command.getUserId(), command.getProjectId()).orElseThrow(() -> new IncorrectIdInputException("Such user does not exists in this project"));
        projectMember.setRole(command.getRole());
        projectMemberRepository.save(projectMember);
    }
}
