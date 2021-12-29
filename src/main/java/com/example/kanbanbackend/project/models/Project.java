package com.example.kanbanbackend.project.models;

import com.example.kanbanbackend.list.models.List;
import lombok.*;
import org.hibernate.annotations.Type;

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
@Setter
public class Project {
    @Type(type="org.hibernate.type.UUIDCharType")
    @Id
    private UUID id;
    private Timestamp createdAt;
    private String name;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<List> listSet;
}
