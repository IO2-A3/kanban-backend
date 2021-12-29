package com.example.kanbanbackend.list.models;

import com.example.kanbanbackend.project.models.Project;
import com.example.kanbanbackend.task.models.Task;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class List {
    @Type(type="org.hibernate.type.UUIDCharType")
    @Id
    private UUID id;

    @ManyToOne
    @JsonBackReference
    private Project project;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Task> taskSet;

    private String name; //todo: unique
    private Integer listOrder; // order to keyword w sqlu
}
