package com.example.kanbanbackend.project.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ProjectInputDTO {
    @NotBlank
    private String name;
}
