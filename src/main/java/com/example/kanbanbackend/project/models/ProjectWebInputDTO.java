package com.example.kanbanbackend.project.models;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ProjectWebInputDTO {
    @NotBlank
    private String name;
}
