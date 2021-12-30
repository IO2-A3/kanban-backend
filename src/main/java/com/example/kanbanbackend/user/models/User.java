package com.example.kanbanbackend.user.models;

import com.example.kanbanbackend.project.ProjectMember.models.ProjectMember;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "UserData")
public class User {

    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private Timestamp createdAt;

    @JsonBackReference
    @OneToMany(mappedBy = "id.user")
    private Set<ProjectMember> projectMembers;
}
