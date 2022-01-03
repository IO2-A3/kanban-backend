package com.example.kanbanbackend.task.models;

import com.example.kanbanbackend.list.models.List;
import com.example.kanbanbackend.task.taskComment.models.TaskComment;
import com.example.kanbanbackend.user.models.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Task {
    @Type(type="org.hibernate.type.UUIDCharType")
    @Id
    private UUID id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private List list;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "task_member",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<User> users;

    @JsonBackReference
    @OneToMany(mappedBy = "task",
            fetch = FetchType.LAZY)
    private Set<TaskComment> comments;

    private String name;
    private String description; //todo: string -> text
    private Integer listOrder;
    private Timestamp dueDate;


    public void addUser(User u) {
        users.add(u);
    }

    public void deleteAnUser(User u) {
        users.remove(u);
    }
}