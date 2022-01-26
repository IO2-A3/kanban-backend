package com.example.kanbanbackend.task.models;

import com.example.kanbanbackend.list.models.List;
import com.example.kanbanbackend.task.taskComment.models.TaskComment;
import com.example.kanbanbackend.user.models.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Task {
    @Type(type="org.hibernate.type.UUIDCharType")
    @Id
    private UUID id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List list;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "task_member",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<User> users;

    @JsonBackReference
    @OneToMany(mappedBy = "task",
            fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<TaskComment> comments;

    private String name;
    private String description; //todo: string -> text
    private int listOrder;
    private Timestamp dueDate;


    public void addUser(User u) {
        users.add(u);
    }

    public void deleteAnUser(User u) {
        users.remove(u);
    }
}