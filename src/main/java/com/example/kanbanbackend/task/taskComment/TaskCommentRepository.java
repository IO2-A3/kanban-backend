package com.example.kanbanbackend.task.taskComment;

import com.example.kanbanbackend.task.taskComment.models.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskCommentRepository extends JpaRepository<TaskComment, UUID> {
}
