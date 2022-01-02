package com.example.kanbanbackend.task.taskComment.models;

import com.example.kanbanbackend.task.models.Task;
import com.example.kanbanbackend.user.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TaskComment {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "task_Id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Timestamp createdAt;

    private Timestamp editedAt;
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
