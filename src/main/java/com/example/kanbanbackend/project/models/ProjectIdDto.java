package com.example.kanbanbackend.project.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ProjectIdDto {
    private Timestamp createdAt;
    private String name;
}
