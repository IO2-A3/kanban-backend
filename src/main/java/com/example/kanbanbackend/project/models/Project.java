package com.example.kanbanbackend.project.models;

import com.example.kanbanbackend.project.ProjectMember.models.ProjectMember;
import com.example.kanbanbackend.user.models.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter
public class Project {
    @Id
    private UUID id;
    private Timestamp createdAt;
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "id.project",
            fetch = FetchType.EAGER)
    private Set<ProjectMember> projectMembers;
}