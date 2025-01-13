package com.innovetsolutionstech.taskearnersng.tasks_service.model.mapper;

import com.innovetsolutionstech.taskearnersng.tasks_service.entity.TaskType;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskType;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.TaskResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TaskTypeMapper {
    public TaskType toTaskType(NewTaskType record) {
        return TaskType.builder()
                .taskId(UUID.randomUUID().toString())
                .taskName(record.taskName())
                .taskDescription(record.taskDescription())
                .taskPrice(record.taskPrice())
                .appName(record.appName())
                .priceType(record.priceType())
                .platform(record.platform())
                .dateCreated(LocalDateTime.now())
                .build();
    }

    public TaskResponse fromTaskType(TaskType taskType) {
        return new TaskResponse(
                taskType.getTaskId(),
                taskType.getTaskName(),
                taskType.getTaskDescription(),
                taskType.getPriceType(),
                taskType.getTaskPrice(),
                taskType.getPlatform(),
                taskType.getDateCreated().toString(),
                taskType.getAppName()
        );
    }
}
