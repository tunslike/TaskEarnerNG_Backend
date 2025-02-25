package com.innovetsolutionstech.taskearnersng.tasks_service.model.mapper;

import com.innovetsolutionstech.taskearnersng.tasks_service.entity.TaskType;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.NewTaskType;
import com.innovetsolutionstech.taskearnersng.tasks_service.model.dto.TaskTypeResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TaskTypeMapper {

    // map to task type
    public TaskType toTaskType(NewTaskType record) {
        return TaskType.builder()
                .taskId(UUID.randomUUID().toString())
                .taskCategory(record.taskCategory())
                .taskName(record.taskName())
                .taskDescription(record.taskDescription())
                .image_src(record.imageSource())
                .taskPrice(record.taskPrice())
                .appName(record.appName())
                .priceType(record.priceType())
                .platform(record.platform())
                .dateCreated(LocalDateTime.now())
                .build();
    }

    // fetch from task type mapper
    public TaskTypeResponse fromTaskType(TaskType taskType) {
        return new TaskTypeResponse(
                taskType.getTaskId(),
                taskType.getTaskCategory(),
                taskType.getTaskName(),
                taskType.getTaskDescription(),
                taskType.getImage_src(),
                taskType.getPriceType(),
                taskType.getTaskPrice(),
                taskType.getPlatform(),
                taskType.getAppName(),
                taskType.getDateCreated().toString()
        );
    }
}
