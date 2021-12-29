package com.example.kanbanbackend.project.ProjectMember.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProjectMember {
    @Id
    private String id;
    private String userId;
    private String projectId;
    private ProjectRole role;

    public void setRole(ProjectRole role) {
        this.role = role;
    }
}
