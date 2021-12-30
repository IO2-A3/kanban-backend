package com.example.kanbanbackend.project.ProjectMember.models;

import com.example.kanbanbackend.project.models.Project;
import com.example.kanbanbackend.user.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ProjectMemberKey implements Serializable {
    @ManyToOne
    private User user;

    @ManyToOne
    private Project project;
}
