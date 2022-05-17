package com.example.kanbanbackend.project;

import com.example.kanbanbackend.authentication.JwtUtil;
import com.example.kanbanbackend.list.ListService;
import com.example.kanbanbackend.list.models.ListIdDto;
import com.example.kanbanbackend.list.models.ListInputDto;
import com.example.kanbanbackend.list.models.ListSetDto;
import com.example.kanbanbackend.project.models.ProjectIdDto;
import com.example.kanbanbackend.project.models.ProjectInputDTO;
import com.example.kanbanbackend.project.models.ProjectSetDto;
import com.example.kanbanbackend.security.OwnerPermission;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    public Set<ProjectSetDto> getProjects(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities();
        System.out.println(authentication.getAuthorities());
        // Checking if anonymous (no token provided, if invalid token provided server responds with an error)
//        System.out.println(authentication instanceof AnonymousAuthenticationToken);
        return projectService.findProjects();
    }

    @GetMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ProjectIdDto getProject(@PathVariable UUID id){
        return projectService.findProject(id);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public UUID addProject(@Valid @RequestBody ProjectInputDTO projectInputDTO, HttpServletRequest request) throws Exception {
        var userId = jwtUtil.getIdFromRequest(request);
        projectInputDTO.setUserId(userId);
        return projectService.createProject(projectInputDTO);
    }
    ///listy
//    @GetMapping("/list")
//    @PreAuthorize("isAuthenticated()")
//    public Set<ListSetDto> getLists(){
//        return listService.findLists();
//    }
//
//    @GetMapping("/list/{id}")
//    @PreAuthorize("isAuthenticated()")
//    public ListIdDto getListById(@PathVariable UUID id){
//        return listService.findListById(id);
//    }

    @PostMapping("/list")
    @PreAuthorize("isAuthenticated()")
    @OwnerPermission(value = "#inputDto.projectId")
    public UUID addList(@Valid @RequestBody ListInputDto inputDto){
        return listService.createList(inputDto);
    }

//    @DeleteMapping("/list/{id}")
//    public void deleteList(@PathVariable UUID id){
//        listService.removeList(id);
//    }

}
