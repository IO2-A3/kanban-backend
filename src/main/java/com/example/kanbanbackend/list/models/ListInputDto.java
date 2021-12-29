package com.example.kanbanbackend.list.models;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class ListInputDto {
    @NotBlank
    private String name;
    @NotNull
    private String projectId;
}