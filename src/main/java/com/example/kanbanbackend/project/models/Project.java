package com.example.kanbanbackend.project.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter
public class Project {
    @Id
    private String id;
    private Timestamp createdAt;
    private String name;
}