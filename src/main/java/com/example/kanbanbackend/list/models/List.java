package com.example.kanbanbackend.list.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class List {
    @Id
    private String id;
    private String projectId;
    private String name;
    private int listOrder; // order to keyword w sqlu

}
