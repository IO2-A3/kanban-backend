package com.example.kanbanbackend.project.models;


import com.example.kanbanbackend.list.models.List;
import com.example.kanbanbackend.project.ProjectMember.models.ProjectMember;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter
@Setter
public class Project {
    @Type(type="org.hibernate.type.UUIDCharType")
    @Id
    private UUID id;
    private Timestamp createdAt;
    private String name;


    @JsonBackReference
    @OneToMany(mappedBy = "id.project",
            fetch = FetchType.EAGER)
    private Set<ProjectMember> projectMembers;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<List> listSet;
}
