package com.example.kanbanbackend.project.models;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ProjectInputDTO {
    @NotBlank
    private String name;
}
