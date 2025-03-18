package com.innovetsolutionstech.taskearnersng.tasks_service.model.mapper;

import com.innovetsolutionstech.taskearnersng.tasks_service.entity.TaskSession;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.TaskSessionModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TaskSessionMapper {

    public TaskSession SaveSession(TaskSessionModel session) {

        return TaskSession.builder()
                .sessionId(UUID.randomUUID().toString())
                .taskId(session.taskID())
                .subscriberId(session.subscriberId())
                .dateCreated(LocalDateTime.now())
                .build();
    }
}
