package com.example.kanbanbackend.project.ProjectMember.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProjectMember {
    @EmbeddedId
    ProjectMemberKey id;

    private ProjectRole role;

    public void setRole(ProjectRole role) {
        this.role = role;
    }
}
