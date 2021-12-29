package com.example.kanbanbackend.project.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter
public class Project {
    @Type(type="org.hibernate.type.UUIDCharType")
    @Id
    private UUID id;
    private Timestamp createdAt;
    private String name;
}